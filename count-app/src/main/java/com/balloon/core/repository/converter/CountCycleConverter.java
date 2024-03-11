package com.balloon.core.repository.converter;

import com.balloon.common.util.JsonUtil;
import com.balloon.core.repository.model.CountCycleModel;
import com.balloon.core.repository.model.CycleInfoModel;
import com.balloon.integration.dal.count_user.entity.CountCycleEntity;
import org.springframework.beans.BeanUtils;

import java.util.Map;

/**
 * @author 王思远
 * @date 2024-02-28 15:57
 */
public class CountCycleConverter {

    public static CountCycleModel convertToModel(CountCycleEntity entity) {
        if (entity == null) {
            return null;
        }
        CountCycleModel model = new CountCycleModel();
        BeanUtils.copyProperties(entity, model, "cycleInfo", "extendInfo");
        model.setCycleInfo(JsonUtil.toObject(entity.getCountCycleInfo(), CycleInfoModel.class));
        model.setExtendInfo(JsonUtil.toObject(entity.getExtendInfo(), Map.class));
        return model;
    }

    public static CountCycleEntity convertToEntity(CountCycleModel model) {
        if (model == null) {
            return null;
        }
        CountCycleEntity entity = new CountCycleEntity();
        BeanUtils.copyProperties(model, entity, "countRule", "extendInfo");
        entity.setCountCycleInfo(JsonUtil.toJson(model.getCycleInfo()));
        entity.setExtendInfo(JsonUtil.toJson(model.getExtendInfo()));
        return entity;
    }


}
