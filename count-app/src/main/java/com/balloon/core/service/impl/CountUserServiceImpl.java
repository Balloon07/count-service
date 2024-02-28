package com.balloon.core.service.impl;

import java.util.Date;
import java.util.List;

import com.balloon.common.CountException;
import com.balloon.common.enums.CountErrorEnum;
import com.balloon.common.util.CycleTimeUtil;
import com.balloon.core.repository.CountConfigRepository;
import com.balloon.core.repository.CountCycleRepository;
import com.balloon.core.repository.CountRecordRepository;
import com.balloon.core.repository.model.CountConfigModel;
import com.balloon.core.repository.model.CountCycleModel;
import com.balloon.core.repository.model.CountRuleModel;
import com.balloon.core.repository.model.CycleInfoModel;
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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * @author 王思远
 * @date 2024-02-26 20:56
 */
@Service
public class CountUserServiceImpl implements CountUserService {

    @Autowired
    private CountConfigRepository configRepository;
    @Autowired
    private CountRecordRepository recordRepository;
    @Autowired
    private CountCycleRepository cycleRepository;

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
        CountCycleModel countCycle = cycleRepository.queryCountCycle(param.getCountId(), param.getDimensionId());
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
    public CountInsertDto insertCount(CountInsertParam param) {
        return null;
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
