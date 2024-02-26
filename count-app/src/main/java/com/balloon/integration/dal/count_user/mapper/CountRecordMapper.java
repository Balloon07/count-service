package com.balloon.integration.dal.count_user.mapper;

import com.balloon.integration.dal.count_user.entity.CountRecordEntity;

public interface CountRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CountRecordEntity record);

    int insertSelective(CountRecordEntity record);

    CountRecordEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CountRecordEntity record);

    int updateByPrimaryKey(CountRecordEntity record);
}