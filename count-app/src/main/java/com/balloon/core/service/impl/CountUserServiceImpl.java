package com.balloon.core.service.impl;

import com.balloon.core.service.CountUserService;
import com.balloon.count.api.client.dto.CountInsertDto;
import com.balloon.count.api.client.dto.CountQueryDto;
import com.balloon.count.api.client.dto.CountRollbackDto;
import com.balloon.count.api.client.dto.CountUserRecordDto;
import com.balloon.count.api.client.param.CountInsertParam;
import com.balloon.count.api.client.param.CountQueryParam;
import com.balloon.count.api.client.param.CountRollbackParam;
import com.balloon.count.api.client.param.CountUserRecordParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王思远
 * @date 2024-02-26 20:56
 */
@Service
public class CountUserServiceImpl implements CountUserService {

    @Autowired
    //private CountUserRepository countUserRepository;

    @Override
    public CountQueryDto queryCount(CountQueryParam param) {
        return null;
    }

    @Override
    public CountInsertDto insertCount(CountInsertParam param) {
        return null;
    }

    @Override
    public CountRollbackDto rollbackCount(CountRollbackParam param) {
        return null;
    }

    @Override
    public CountUserRecordDto queryUserCountRecord(CountUserRecordParam param) {
        return null;
    }
}
