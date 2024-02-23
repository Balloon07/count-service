package com.balloon.integration.dal.count_config;

import com.balloon.integration.dal.count_config.entity.CountConfigEntity;
import com.balloon.integration.dal.count_config.mapper.CountConfigMapper;

/**
 * @author 王思远
 * @date 2023-12-16 17:34
 */
public interface CountConfigDao extends CountConfigMapper {

    int updateByCountId(CountConfigEntity entity);

    CountConfigEntity getByCountId(String countId);
}
