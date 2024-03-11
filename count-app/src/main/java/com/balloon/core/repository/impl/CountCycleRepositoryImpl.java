package com.balloon.core.repository.impl;

import com.balloon.core.repository.CountCycleRepository;
import com.balloon.core.repository.converter.CountCycleConverter;
import com.balloon.core.repository.model.CountCycleModel;
import com.balloon.integration.dal.count_user.CountCycleDao;
import com.balloon.integration.dal.count_user.entity.CountCycleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王思远
 * @date 2024-02-27 16:09
 */
@Service
public class CountCycleRepositoryImpl implements CountCycleRepository {

    @Autowired
    private CountCycleDao countCycleDao;

    @Override
    public int insert(CountCycleModel countCycle) {
        CountCycleEntity entity = CountCycleConverter.convertToEntity(countCycle);
        return countCycleDao.insertSelective(entity);
    }

    @Override
    public int updateByCountIdDimensionId(String countId, String dimensionId, String cycleInfo) {
        return countCycleDao.updateByCountIdDimensionId(countId, dimensionId, cycleInfo);
    }

    @Override
    public CountCycleModel queryByCountIdDimensionId(String countId, String dimensionId) {
        CountCycleEntity entity = countCycleDao.getByCountIdDimensionId(countId, dimensionId);
        return CountCycleConverter.convertToModel(entity);
    }
}
