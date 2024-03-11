package com.balloon.integration.dal.count_user;

import com.balloon.core.repository.model.CountRecordModel;
import com.balloon.integration.dal.count_user.entity.CountRecordEntity;
import com.balloon.integration.dal.count_user.mapper.CountRecordMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 王思远
 * @date 2024-03-11 15:43
 */
public interface CountRecordDao extends CountRecordMapper {

    CountRecordEntity getByRequestNo(@Param("requestNo") String requestNo);
}
