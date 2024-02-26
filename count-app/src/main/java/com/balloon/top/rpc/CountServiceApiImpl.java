package com.balloon.top.rpc;

import com.balloon.common.util.ExceptionUtil;
import com.balloon.common.util.JsonUtil;
import com.balloon.core.service.CountUserService;
import com.balloon.count.api.client.CountServiceApi;
import com.balloon.count.api.client.dto.CountInsertDto;
import com.balloon.count.api.client.dto.CountQueryDto;
import com.balloon.count.api.client.dto.CountRollbackDto;
import com.balloon.count.api.client.dto.CountUserRecordDto;
import com.balloon.count.api.client.param.CountInsertParam;
import com.balloon.count.api.client.param.CountQueryParam;
import com.balloon.count.api.client.param.CountRollbackParam;
import com.balloon.count.api.client.param.CountUserRecordParam;
import com.balloon.count.api.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王思远
 * @date 2024-02-26 20:46
 */
@Slf4j
@Service
public class CountServiceApiImpl implements CountServiceApi {

    @Autowired
    private CountUserService countUserService;

    @Override
    public Result<CountQueryDto> queryCount(CountQueryParam param) {
        try {
            CountQueryDto result = countUserService.queryCount(param);
            log.info("计次查询, param={} result={}", JsonUtil.toJson(param), JsonUtil.toJson(result));
            return Result.ofSuccess(result);
        } catch (Throwable e) {
            log.error("计次查询, param={}", JsonUtil.toJson(param), e);
            return ExceptionUtil.toResult(e);
        }
    }

    @Override
    public Result<CountInsertDto> insertCount(CountInsertParam param) {
        return null;
    }

    @Override
    public Result<CountRollbackDto> rollbackCount(CountRollbackParam param) {
        return null;
    }

    @Override
    public Result<CountUserRecordDto> queryUserCountRecord(CountUserRecordParam param) {
        return null;
    }
}

