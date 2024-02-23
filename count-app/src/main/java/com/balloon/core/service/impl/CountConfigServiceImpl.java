package com.balloon.core.service.impl;

import com.balloon.common.CountException;
import com.balloon.common.enums.CountErrorEnum;
import com.balloon.common.util.IDGenerator;
import com.balloon.core.repository.CountConfigRepository;
import com.balloon.core.repository.model.CountConfigModel;
import com.balloon.core.repository.model.CountRuleModel;
import com.balloon.core.service.CountConfigService;
import com.balloon.count.api.admin.dto.CountConfigDto;
import com.balloon.count.api.admin.dto.CountRuleDto;
import com.balloon.count.api.admin.param.CountConfigParam;
import com.balloon.count.api.common.enums.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author 王思远
 * @date 2023-12-16 18:40
 */
@Service
public class CountConfigServiceImpl implements CountConfigService {

    @Autowired
    private CountConfigRepository countConfigRepository;

    @Override
    public String generateId(String countType) {
        Assert.notNull(CountTypeEnum.getByName(countType), "计次类型countType不能为空且必须符合约定类型");
        return countType + IDGenerator.generateId();
    }

    @Override
    public String saveConfig(CountConfigParam param) {
        // 参数校验
        checkSaveConfigParam(param);

        CountConfigModel model = convertCountConfigModel(param);
        if (StringUtils.isNotBlank(param.getCountId())) {
            // 更新配置
            model.setUpdater(param.getOperator());
            CountConfigModel oldConfig = countConfigRepository.getByCountId(model.getCountId());
            if (oldConfig == null) {
                throw new CountException(CountErrorEnum.CONFIG_NOT_FUND, model.getCountId());
            }
            countConfigRepository.updateByCountId(model);
        } else {
            // 新增配置
            model.setCountId(generateId(model.getCountType()));
            model.setCreator(param.getOperator());
            model.setUpdater(param.getOperator());
            countConfigRepository.create(model);
        }
        return model.getCountId();
    }

    @Override
    public CountConfigDto queryConfig(String countId) {
        Assert.hasText(countId, "计次countId不能为空");
        CountConfigModel configModel = countConfigRepository.getByCountId(countId);
        CountConfigDto configDto = new CountConfigDto();
        BeanUtils.copyProperties(configModel, configDto, "countRule");
        CountRuleDto countRule = new CountRuleDto();
        BeanUtils.copyProperties(configModel.getCountRule(), countRule);
        configDto.setCountRule(countRule);
        return configDto;
    }

    private CountConfigModel convertCountConfigModel(CountConfigParam param) {
        CountConfigModel model = new CountConfigModel();
        BeanUtils.copyProperties(param, model, "countRule");
        model.setState(CountStateEnum.going.name());
        CountRuleModel countRule = new CountRuleModel();
        countRule.setCycleType(param.getCycleType());
        countRule.setTimeInterval(param.getTimeInterval());
        countRule.setTimeUnit(param.getTimeUnit());
        countRule.setTimeTotal(param.getTimeTotal());
        model.setCountRule(countRule);
        return model;
    }

    private void checkSaveConfigParam(CountConfigParam param) {
        Assert.notNull(CountTypeEnum.getByName(param.getCountType()), "计次类型countType不能为空且必须符合约定类型");
        Assert.hasText(param.getCountName(), "计次名称countName不能为空");
        Assert.notNull(DimensionTypeEnum.getByName(param.getDimensionType()), "计次维度dimensionType不能为空且必须符合约定类型");
        Assert.notNull(param.getStartTime(), "计次周期startTime不能为空");
        Assert.notNull(param.getEndTime(), "计次周期endTime不能为空");
        Assert.notNull(CycleTypeEnum.getByName(param.getCycleType()), "周期类型cycleType不能为空且必须符合约定类型");
        Assert.notNull(param.getTimeInterval(), "周期间隔timeInterval不能为空");
        Assert.notNull(TimeUnitEnum.getByName(param.getTimeUnit()), "周期单位timeUnit不能为空且必须符合约定类型");
        Assert.notNull(param.getTimeTotal(), "周期次数timeTotal不能为空");
        Assert.hasText(param.getOperator(), "操作人operator不能为空");
    }
}
