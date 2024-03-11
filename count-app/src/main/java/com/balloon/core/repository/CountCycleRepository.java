package com.balloon.core.repository;

import com.balloon.core.repository.model.CountCycleModel;

/**
 * @author 王思远
 * @date 2024-02-27 16:09
 */
public interface CountCycleRepository {

    int insert(CountCycleModel countCycle);

    int updateByCountIdDimensionId(String countId, String dimensionId, String cycleInfo);

    CountCycleModel queryByCountIdDimensionId(String countId, String dimensionId);
}
