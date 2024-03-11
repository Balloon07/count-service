package com.balloon.core.repository.converter;

import com.balloon.common.util.JsonUtil;
import com.balloon.core.repository.model.CountRecordModel;
import com.balloon.integration.dal.count_user.entity.CountRecordEntity;
import org.springframework.beans.BeanUtils;

import java.util.Map;

/**
 * @author 王思远
 * @date 2024-03-11 15:50
 */
public class CountRecordConverter {

    public static CountRecordEntity convertToEntity(CountRecordModel model) {
        if (model == null) {
            return null;
        }
        CountRecordEntity entity = new CountRecordEntity();
        BeanUtils.copyProperties(model, entity, "extendInfo");
        entity.setExtendInfo(JsonUtil.toJson(model.getExtendInfo()));
        return entity;
    }

    public static CountRecordModel convertToModel(CountRecordEntity entity) {
        if (entity == null) {
            return null;
        }
        CountRecordModel model = new CountRecordModel();
        BeanUtils.copyProperties(entity, model, "extendInfo");
        model.setExtendInfo(JsonUtil.toObject(entity.getExtendInfo(), Map.class));
        return model;
    }
}
