package com.balloon.top.rpc;

import com.balloon.common.util.ExceptionUtil;
import com.balloon.common.util.JsonUtil;
import com.balloon.core.service.CountService;
import com.balloon.count.api.admin.CountConfigApi;
import com.balloon.count.api.admin.param.CountConfigParam;
import com.balloon.count.api.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王思远
 * @date 2023-12-16 18:39
 */
@Service
@Slf4j
public class CountConfigApiImpl implements CountConfigApi {

    @Autowired
    private CountService countService;

    @Override
    public Result<String> generateCountId(String countType) {
        try {
            String result = countService.generateId(countType);
            log.info("生成计次配置id, countType={} result={}", countType, result);
            return Result.ofSuccess(result);
        } catch (Throwable e) {
            log.error("计次服务-生成计次配置id异常, countType={}", countType, e);
            return ExceptionUtil.toResult(e);
        }
    }

    @Override
    public Result<String> createCountConfig(CountConfigParam param) {
        try {
            String result = countService.createConfig(param);
            log.info("创建计次配置, param={} result={}", JsonUtil.toJson(param), result);
            return Result.ofSuccess(null);
        } catch (Throwable e) {
            log.error("创建计次配置异常, param={}", JsonUtil.toJson(param), e);
            return ExceptionUtil.toResult(e);
        }
    }
}
