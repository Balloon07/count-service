package com.balloon.core.service;

import com.balloon.count.api.client.dto.CountInsertDto;
import com.balloon.count.api.client.dto.CountQueryDto;
import com.balloon.count.api.client.dto.CountRollbackDto;
import com.balloon.count.api.client.dto.CountUserRecordDto;
import com.balloon.count.api.client.param.CountInsertParam;
import com.balloon.count.api.client.param.CountQueryParam;
import com.balloon.count.api.client.param.CountRollbackParam;
import com.balloon.count.api.client.param.CountUserRecordParam;

/**
 * @author 王思远
 * @date 2024-02-26 20:56
 */
public interface CountUserService {

    /**
     * 计次查询
     *
     * @param param
     * @return
     */
    CountQueryDto queryCount(CountQueryParam param);

    /**
     * 计次准入
     *
     * @param param
     * @return
     */
    CountInsertDto insertCount(CountInsertParam param);

    /**
     * 计次逆向
     *
     * @param param
     * @return
     */
    CountRollbackDto rollbackCount(CountRollbackParam param);

    /**
     * 用户计次纪录查询
     *
     * @param param
     * @return
     */
    CountUserRecordDto queryUserCountRecord(CountUserRecordParam param);

}
