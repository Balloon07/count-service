package com.balloon.integration.dal.count_user;

import com.balloon.integration.dal.count_user.entity.CountCycleEntity;
import com.balloon.integration.dal.count_user.mapper.CountCycleMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 王思远
 * @date 2024-02-28 15:54
 */
public interface CountCycleDao extends CountCycleMapper {

    CountCycleEntity getByCountIdDimensionId(@Param("countId") String countId,
                                             @Param("dimensionId") String dimensionId);

    int updateByCountIdDimensionId(@Param("countId") String countId,
                                   @Param("dimensionId") String dimensionId,
                                   @Param("cycleInfo") String cycleInfo);
}
