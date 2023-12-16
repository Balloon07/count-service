package com.balloon.integration.dal.count_config.mapper;

import com.balloon.integration.dal.count_config.entity.CountConfigEntity;

public interface CountConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CountConfigEntity record);

    int insertSelective(CountConfigEntity record);

    CountConfigEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CountConfigEntity record);

    int updateByPrimaryKey(CountConfigEntity record);
}