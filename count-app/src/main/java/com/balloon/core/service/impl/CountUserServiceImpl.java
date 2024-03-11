package com.balloon.core.service.impl;

import com.balloon.common.CountException;
import com.balloon.common.enums.CountErrorEnum;
import com.balloon.common.util.CycleTimeUtil;
import com.balloon.common.util.IDGenerator;
import com.balloon.common.util.JsonUtil;
import com.balloon.core.repository.CountConfigRepository;
import com.balloon.core.repository.CountCycleRepository;
import com.balloon.core.repository.CountRecordRepository;
import com.balloon.core.repository.model.*;
import com.balloon.core.service.CountUserService;
import com.balloon.count.api.client.dto.CountInsertDto;
import com.balloon.count.api.client.dto.CountQueryDto;
import com.balloon.count.api.client.dto.CountRollbackDto;
import com.balloon.count.api.client.dto.CountUserRecordDto;
import com.balloon.count.api.client.param.CountInsertParam;
import com.balloon.count.api.client.param.CountQueryParam;
import com.balloon.count.api.client.param.CountRollbackParam;
import com.balloon.count.api.client.param.CountUserRecordParam;
import com.balloon.count.api.common.enums.CountStateEnum;
import com.balloon.count.api.common.enums.CycleTypeEnum;
import com.balloon.count.api.common.enums.TimeUnitEnum;
import com.balloon.integration.dal.count_user.entity.CountRecordEntity;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author 王思远
 * @date 2024-02-26 20:56
 */
@Service
@Slf4j
public class CountUserServiceImpl implements CountUserService {

    @Autowired
    private CountConfigRepository configRepository;
    @Autowired
    private CountRecordRepository recordRepository;
    @Autowired
    private CountCycleRepository cycleRepository;
    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public CountQueryDto queryCount(CountQueryParam param) {
        Assert.hasText(param.getCountId(), "计次countId不能为空");
        Assert.hasText(param.getDimensionId(), "计次维度id不能为空");

        // 查询计次配置
        CountConfigModel configModel = configRepository.getByCountId(param.getCountId());
        if (configModel == null) {
            throw new CountException(CountErrorEnum.CONFIG_NOT_FUND, param.getCountId());
        }
        if (!StringUtils.equals(configModel.getState(), CountStateEnum.going.name())) {
            throw new CountException(CountErrorEnum.CONFIG_NOT_GOING_STATE, param.getCountId());
        }
        // 查询计次周期记录
        CountCycleModel countCycle = cycleRepository.queryByCountIdDimensionId(param.getCountId(), param.getDimensionId());
        // 统计当前周期内有效的计次
        Date occurTime = param.getOccurTime() != null ? param.getOccurTime() : new Date();
        Integer currentCount = countCycle != null ? calEffectiveCount(configModel.getCountRule(), countCycle.getCycleInfo(), occurTime) : 0;

        // 封装信息
        CountQueryDto result = new CountQueryDto();
        result.setCountId(param.getCountId());
        result.setDimensionId(param.getDimensionId());
        result.setState(configModel.getState());
        result.setStartTime(configModel.getStartTime());
        result.setEndTime(configModel.getEndTime());
        result.setTimeInterval(configModel.getCountRule().getTimeInterval());
        result.setTimeUnit(configModel.getCountRule().getTimeUnit());
        result.setTimeLimitCount(configModel.getCountRule().getTimeTotal());
        result.setCurrentCount(currentCount);
        return result;
    }

    /**
     * 统计当前周期内有效的计次
     *
     * @param countRule
     * @param cycleInfo
     * @param occurTime
     * @return
     */
    private Integer calEffectiveCount(CountRuleModel countRule, CycleInfoModel cycleInfo, Date occurTime) {
        Integer countNum = 0;
        if (StringUtils.equals(TimeUnitEnum.life.name(), countRule.getCycleType())) {
            // 1.timeUnit=life
            countNum = cycleInfo.getCycleCount();
        } else if (StringUtils.equals(CycleTypeEnum.natural.name(), countRule.getCycleType())
                && countRule.getTimeInterval() == 1) {
            // 2.cycleType=natural & timeInterval=1
            String cycleTime = CycleTimeUtil.parseCycleValue(occurTime, countRule.getTimeUnit());
            countNum = StringUtils.equals(cycleTime, cycleInfo.getCycleTime()) ? cycleInfo.getCycleCount() : 0;
        } else {
            // 3.cycleType=natural&&timeInterval!=1 || timeUnit=relative
            List<CycleInfoModel.CycleRecord> recordList = cycleInfo.getCycleRecordList();
            if (CollectionUtils.isEmpty(recordList)) {
                countNum = 0;
            } else {
                Pair<Date, Date> currentCycle = CycleTimeUtil.parseCurrentCycle(occurTime, countRule);
                countNum = recordList.stream().filter(record -> record.getRecordTime().after(currentCycle.getLeft()))
                        .map(CycleInfoModel.CycleRecord::getRecordNum).reduce(0, Integer::sum);
            }
        }
        return countNum;
    }

    @Override
    @Transactional(transactionManager = "countUserTransactionManager", rollbackFor = Exception.class)
    public CountInsertDto insertCount(CountInsertParam param) {
        Assert.hasText(param.getCountId(), "计次countId不能为空");
        Assert.hasText(param.getDimensionId(), "计次维度id不能为空");
        Assert.notNull(param.getCountNum(), "计次数量countNum不能为空");

        // 查询计次配置
        CountConfigModel configModel = configRepository.getByCountId(param.getCountId());
        if (configModel == null) {
            throw new CountException(CountErrorEnum.CONFIG_NOT_FUND, param.getCountId());
        }
        if (!StringUtils.equals(configModel.getState(), CountStateEnum.going.name())) {
            throw new CountException(CountErrorEnum.CONFIG_NOT_GOING_STATE, param.getCountId());
        }
        String key = String.join(".", "count", param.getCountId(), param.getDimensionId());
        Boolean lock = redisTemplate.opsForValue().setIfAbsent(key, Strings.EMPTY, 10, TimeUnit.SECONDS);
        if (lock == null || !lock) {
            throw new CountException(CountErrorEnum.BUSY_OPERATION);
        }
        try {
            // 查询计次流水
            if (StringUtils.isNotBlank(param.getRequestNo())) {
                CountRecordModel recordModel = recordRepository.queryByRequestNo(param.getRequestNo());
                if (recordModel != null) {
                    throw new CountException(CountErrorEnum.COUNT_INSERT_IDEMPOTENT, param.getCountId(), param.getDimensionId());
                }
            }

            // 查询计次周期记录
            CountCycleModel countCycle = cycleRepository.queryByCountIdDimensionId(param.getCountId(), param.getDimensionId());
            Date occurTime = param.getOccurTime() != null ? param.getOccurTime() : new Date();
            CountRuleModel countRule = configModel.getCountRule();
            Integer currentCount = 0;
            if (countCycle == null) {
                // 插入计次流水，计次周期记录
                recordRepository.insert(buildCountRecord(configModel, param));
                CountCycleModel cycleModel = new CountCycleModel();
                cycleModel.setDimensionType(configModel.getDimensionType());
                cycleModel.setDimensionId(param.getDimensionId());
                cycleModel.setCountId(param.getCountId());
                cycleModel.setCountType(configModel.getCountType());
                cycleModel.setCycleInfo(buildCycleInfo(configModel.getCountRule(), null, occurTime, param.getCountNum()));
                cycleRepository.insert(cycleModel);
            } else {
                // 统计当前周期内有效的计次，判断是否达到计次上限
                currentCount = calEffectiveCount(countRule, countCycle.getCycleInfo(), occurTime);
                if (currentCount + param.getCountNum() > countRule.getTimeTotal()) {
                    throw new CountException(CountErrorEnum.COUNT_EXCEED_LIMIT, param.getCountId(), param.getDimensionId());
                }
                // 插入计次流水，更新计次周期记录
                recordRepository.insert(buildCountRecord(configModel, param));
                CycleInfoModel cycleInfo = buildCycleInfo(configModel.getCountRule(), countCycle.getCycleInfo(), occurTime, param.getCountNum());
                cycleRepository.updateByCountIdDimensionId(param.getCountId(), param.getDimensionId(), JsonUtil.toJson(cycleInfo));
            }

            // 封装数据
            CountInsertDto result = new CountInsertDto();
            result.setCountId(param.getCountId());
            result.setDimensionId(param.getDimensionId());
            result.setState(configModel.getState());
            result.setStartTime(configModel.getStartTime());
            result.setEndTime(configModel.getEndTime());
            result.setCycleType(countRule.getCycleType());
            result.setTimeInterval(countRule.getTimeInterval());
            result.setTimeUnit(countRule.getTimeUnit());
            result.setTimeLimitCount(countRule.getTimeTotal());
            result.setCurrentCount(currentCount + param.getCountNum());
            return result;
        } finally {
            redisTemplate.delete(key);
        }
    }

    /**
     * 封装当前周期内的计次信息
     *
     * @param countRule
     * @param oldCycleInfo
     * @param occurTime
     * @param countNum
     * @return
     */
    private CycleInfoModel buildCycleInfo(CountRuleModel countRule, CycleInfoModel oldCycleInfo, Date occurTime, Integer countNum) {
        int cycleCount = 0;
        String cycleTime = null;
        List<CycleInfoModel.CycleRecord> cycleRecordList = Lists.newArrayList();

        if (StringUtils.equals(TimeUnitEnum.life.name(), countRule.getCycleType())) {
            // 1.timeUnit=life
            cycleCount = oldCycleInfo != null ? oldCycleInfo.getCycleCount() + countNum : countNum;
            cycleTime = TimeUnitEnum.life.name();

        } else if (StringUtils.equals(CycleTypeEnum.natural.name(), countRule.getCycleType())
                && countRule.getTimeInterval() == 1) {
            // 2.cycleType=natural & timeInterval=1
            cycleTime = CycleTimeUtil.parseCycleValue(occurTime, countRule.getTimeUnit());
            cycleCount = StringUtils.equals(cycleTime, oldCycleInfo.getCycleTime()) ? oldCycleInfo.getCycleCount() + countNum : countNum;
        } else {
            // 3.cycleType=natural&&timeInterval!=1 || timeUnit=relative
            List<CycleInfoModel.CycleRecord> oldRecordList = oldCycleInfo.getCycleRecordList();
            CycleInfoModel.CycleRecord cycleRecord = new CycleInfoModel.CycleRecord();
            cycleRecord.setRecordNum(countNum);
            cycleRecord.setRecordTime(occurTime);
            if (CollectionUtils.isEmpty(oldRecordList)) {
                cycleCount = countNum;
                cycleRecordList.add(cycleRecord);
            } else {
                Pair<Date, Date> currentCycle = CycleTimeUtil.parseCurrentCycle(occurTime, countRule);
                cycleRecordList = oldRecordList.stream().filter(record -> record.getRecordTime().after(currentCycle.getLeft())).collect(Collectors.toList());
                cycleRecordList.add(cycleRecord);
            }
        }
        CycleInfoModel cycleInfo = new CycleInfoModel();
        cycleInfo.setCycleCount(cycleCount);
        cycleInfo.setCycleTime(cycleTime);
        cycleInfo.setCycleRecordList(cycleRecordList);
        return cycleInfo;
    }

    private CountRecordModel buildCountRecord(CountConfigModel configModel, CountInsertParam param) {
        CountRecordModel countRecord = new CountRecordModel();
        countRecord.setRequestNo(IDGenerator.generateUUID());
        countRecord.setDimensionType(configModel.getDimensionType());
        countRecord.setDimensionId(param.getDimensionId());
        countRecord.setCountId(param.getCountId());
        countRecord.setCountType(configModel.getCountType());
        countRecord.setCountNum(param.getCountNum());
        countRecord.setOccurTime(param.getOccurTime() != null ? param.getOccurTime() : new Date());
        return countRecord;
    }

    @Override
    public CountRollbackDto rollbackCount(CountRollbackParam param) {
        return null;
    }

    @Override
    public CountUserRecordDto queryUserCountRecord(CountUserRecordParam param) {
        return null;
    }

}
