package com.balloon.core.repository.impl;

import com.balloon.core.repository.CountRecordRepository;
import com.balloon.core.repository.converter.CountRecordConverter;
import com.balloon.core.repository.model.CountRecordModel;
import com.balloon.integration.dal.count_user.CountRecordDao;
import com.balloon.integration.dal.count_user.entity.CountRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王思远
 * @date 2024-02-27 16:07
 */
@Service
public class CountRecordRepositoryImpl implements CountRecordRepository {

    @Autowired
    private CountRecordDao countRecordDao;

    @Override
    public int insert(CountRecordModel model) {
        CountRecordEntity entity = CountRecordConverter.convertToEntity(model);
        return countRecordDao.insertSelective(entity);
    }

    @Override
    public CountRecordModel queryByRequestNo(String requestNo) {
        CountRecordEntity entity = countRecordDao.getByRequestNo(requestNo);
        return CountRecordConverter.convertToModel(entity);
    }
}
