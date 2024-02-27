package com.balloon.core.repository.impl;

import com.balloon.core.repository.CountRecordRepository;
import com.balloon.core.repository.model.CountCycleModel;
import org.springframework.stereotype.Service;

/**
 * @author 王思远
 * @date 2024-02-27 16:07
 */
@Service
public class CountRecordRepositoryImpl implements CountRecordRepository {

    @Override
    public CountCycleModel queryCountCycle(String countId, String dimensionId) {
        return null;
    }
}
