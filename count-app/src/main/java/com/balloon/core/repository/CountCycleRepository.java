package com.balloon.core.repository;

import com.balloon.core.repository.model.CountCycleModel;

/**
 * @author 王思远
 * @date 2024-02-27 16:09
 */
public interface CountCycleRepository {

    CountCycleModel queryCountCycle(String countId, String dimensionId);
}
