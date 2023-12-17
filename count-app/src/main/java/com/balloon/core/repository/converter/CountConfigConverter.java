package com.balloon.core.repository.converter;

import com.balloon.common.util.JsonUtil;
import com.balloon.core.repository.model.CountConfigModel;
import com.balloon.core.repository.model.CountRuleModel;
import com.balloon.integration.dal.count_config.entity.CountConfigEntity;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 王思远
 * @date 2023-12-18 02:40
 */
public class CountConfigConverter {

    public static CountConfigModel convertToModel(CountConfigEntity entity) {
        if (entity == null) {
            return null;
        }
        CountConfigModel model = new CountConfigModel();
        BeanUtils.copyProperties(entity, model, "countRule", "extendInfo");
        model.setCountRule(JsonUtil.toObject(entity.getCountRule(), CountRuleModel.class));
        model.setExtendInfo(JsonUtil.toObject(entity.getExtendInfo(), Map.class));
        return model;

    }

    public static List<CountConfigModel> convertToModels(List<CountConfigEntity> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Lists.newArrayList();
        }
        List<CountConfigModel> models = new ArrayList<>();
        for (CountConfigEntity entity : entities) {
            models.add(convertToModel(entity));
        }
        return models;
    }

    public static CountConfigEntity convertToEntity(CountConfigModel model) {
        if (model == null) {
            return null;
        }
        CountConfigEntity entity = new CountConfigEntity();
        BeanUtils.copyProperties(model, entity, "countRule", "extendInfo");
        entity.setCountRule(JsonUtil.toJson(model.getCountRule()));
        entity.setExtendInfo(JsonUtil.toJson(model.getExtendInfo()));
        return entity;
    }

    public static List<CountConfigEntity> convertToEntities(List<CountConfigModel> models) {
        if (CollectionUtils.isEmpty(models)) {
            return Lists.newArrayList();
        }

        List<CountConfigEntity> entities = new ArrayList<>();
        for (CountConfigModel model : models) {
            entities.add(convertToEntity(model));
        }
        return entities;
    }
}
