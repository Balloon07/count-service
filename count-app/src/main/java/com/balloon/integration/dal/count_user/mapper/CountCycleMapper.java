package com.balloon.integration.dal.count_user.mapper;

import com.balloon.integration.dal.count_user.entity.CountCycleEntity;

public interface CountCycleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CountCycleEntity record);

    int insertSelective(CountCycleEntity record);

    CountCycleEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CountCycleEntity record);

    int updateByPrimaryKey(CountCycleEntity record);
}