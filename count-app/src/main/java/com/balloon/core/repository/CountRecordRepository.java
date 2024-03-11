package com.balloon.core.repository;

import com.balloon.core.repository.model.CountRecordModel;

/**
 * @author 王思远
 * @date 2024-02-27 16:07
 */
public interface CountRecordRepository {

    int insert(CountRecordModel countRecord);

    CountRecordModel queryByRequestNo(String requestNo);
}
