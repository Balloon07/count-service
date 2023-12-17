package com.balloon.core.service.impl;

import com.balloon.common.util.IDGenerator;
import com.balloon.core.repository.CountConfigRepository;
import com.balloon.core.repository.model.CountConfigModel;
import com.balloon.core.repository.model.CountRuleModel;
import com.balloon.core.service.CountService;
import com.balloon.count.api.admin.param.CountConfigParam;
import com.balloon.count.api.common.enums.CountStateEnum;
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
public class CountServiceImpl implements CountService {

    @Autowired
    private IDGenerator idGenerator;
    @Autowired
    private CountConfigRepository countConfigRepository;


    @Override
    public String generateId(String countType) {
        return countType + idGenerator.generateId();
    }

    @Override
    public String saveConfig(CountConfigParam param) {
        // 参数校验
        checkSaveConfigParam(param);

        // 保存计次配置
        CountConfigModel model = convertCountConfigModel(param);
        if (StringUtils.isNotBlank(param.getCountId())) {
            model.setUpdater(param.getOperator());
            countConfigRepository.updateByCountId(model);
        } else {
            model.setCountId(idGenerator.generateId());
            model.setCreator(param.getOperator());
            model.setUpdater(param.getOperator());
            countConfigRepository.create(model);
        }
        return null;
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
        Assert.hasText(param.getCountType(), "计次类型countType不能为空");
        Assert.hasText(param.getCountName(), "计次名称countName不能为空");
        Assert.hasText(param.getDimensionType(), "计次维度dimensionType不能为空");
        Assert.notNull(param.getStartTime(), "计次周期startTime不能为空");
        Assert.notNull(param.getEndTime(), "计次周期endTime不能为空");
        Assert.hasText(param.getCycleType(), "周期类型cycleType不能为空");
        Assert.notNull(param.getTimeInterval(), "周期间隔timeInterval不能为空");
        Assert.hasText(param.getTimeUnit(), "周期单位timeUnit不能为空");
        Assert.notNull(param.getTimeTotal(), "周期次数timeTotal不能为空");
        Assert.hasText(param.getOperator(), "操作人operator不能为空");
    }
}
