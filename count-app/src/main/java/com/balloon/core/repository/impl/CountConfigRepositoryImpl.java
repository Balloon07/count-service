package com.balloon.core.repository.impl;

import com.balloon.core.repository.CountConfigRepository;
import com.balloon.core.repository.converter.CountConfigConverter;
import com.balloon.core.repository.model.CountConfigModel;
import com.balloon.integration.dal.count_config.CountConfigDao;
import com.balloon.integration.dal.count_config.entity.CountConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王思远
 * @date 2023-12-17 03:23
 */
@Service
public class CountConfigRepositoryImpl implements CountConfigRepository {

    @Autowired
    private CountConfigDao countConfigDao;

    @Override
    public int create(CountConfigModel model) {
        CountConfigEntity entity = CountConfigConverter.convertToEntity(model);
        return countConfigDao.insertSelective(entity);
    }

    @Override
    public int updateByCountId(CountConfigModel model) {
        CountConfigEntity entity = CountConfigConverter.convertToEntity(model);
        return countConfigDao.updateByCountId(entity);
    }

    @Override
    public CountConfigModel getByCountId(String countId) {
        CountConfigEntity entity = countConfigDao.getByCountId(countId);
        return CountConfigConverter.convertToModel(entity);
    }
}
