package com.balloon.count.api.client;

import com.balloon.count.api.client.dto.CountInsertDto;
import com.balloon.count.api.client.dto.CountQueryDto;
import com.balloon.count.api.client.dto.CountRollbackDto;
import com.balloon.count.api.client.dto.CountUserRecordDto;
import com.balloon.count.api.client.param.CountInsertParam;
import com.balloon.count.api.client.param.CountQueryParam;
import com.balloon.count.api.client.param.CountRollbackParam;
import com.balloon.count.api.client.param.CountUserRecordParam;
import com.balloon.count.api.common.Result;

/**
 * 计次服务api
 *
 * @author 王思远
 * @date 2023-12-16 16:51
 */
public interface CountServiceApi {

    /**
     * 计次查询
     *
     * @param param
     * @return
     */
    Result<CountQueryDto> queryCount(CountQueryParam param);

    /**
     * 计次准入
     *
     * @param param
     * @return
     */
    Result<CountInsertDto> insertCount(CountInsertParam param);

    /**
     * 计次逆向
     *
     * @param param
     * @return
     */
    Result<CountRollbackDto> rollbackCount(CountRollbackParam param);

    /**
     * 用户计次纪录查询
     *
     * @param param
     * @return
     */
    Result<CountUserRecordDto> queryUserCountRecord(CountUserRecordParam param);
}
