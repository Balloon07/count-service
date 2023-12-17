package com.balloon.core.repository;

import com.balloon.core.repository.model.CountConfigModel;

/**
 * @author 王思远
 * @date 2023-12-17 03:11
 */
public interface CountConfigRepository {

    String create(CountConfigModel model);

    int updateByCountId(CountConfigModel model);

    CountConfigModel getByCountId(String countId);
}
