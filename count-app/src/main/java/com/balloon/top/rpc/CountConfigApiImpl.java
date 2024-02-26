package com.balloon.top.rpc;

import com.balloon.common.util.ExceptionUtil;
import com.balloon.common.util.JsonUtil;
import com.balloon.core.service.CountConfigService;
import com.balloon.count.api.admin.CountConfigApi;
import com.balloon.count.api.admin.dto.CountConfigDto;
import com.balloon.count.api.admin.param.CountConfigParam;
import com.balloon.count.api.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王思远
 * @date 2023-12-16 18:39
 */
@Slf4j
@Service
public class CountConfigApiImpl implements CountConfigApi {

    @Autowired
    private CountConfigService countConfigService;

    @Override
    public Result<String> generateCountId(String countType) {
        try {
            String countId = countConfigService.generateId(countType);
            log.info("生成计次配置id, countType={} countId={}", countType, countId);
            return Result.ofSuccess(countId);
        } catch (Throwable e) {
            log.error("生成计次配置id异常, countType={}", countType, e);
            return ExceptionUtil.toResult(e);
        }
    }

    @Override
    public Result<String> createCountConfig(CountConfigParam param) {
        try {
            String countId = countConfigService.saveConfig(param);
            log.info("创建计次配置, param={} countId={}", JsonUtil.toJson(param), countId);
            return Result.ofSuccess(countId);
        } catch (Throwable e) {
            log.error("创建计次配置异常, param={}", JsonUtil.toJson(param), e);
            return ExceptionUtil.toResult(e);
        }
    }

    @Override
    public Result<CountConfigDto> queryCountConfig(String countId) {
        try {
            CountConfigDto result = countConfigService.queryConfig(countId);
            log.info("查询计次配置, countId={} result={}", countId, JsonUtil.toJson(result));
            return Result.ofSuccess(result);
        } catch (Throwable e) {
            log.error("查询计次配置异常, countId={}", countId, e);
            return ExceptionUtil.toResult(e);
        }
    }
}
