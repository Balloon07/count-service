package com.balloon.top.inner;

import com.balloon.common.util.ExceptionUtil;
import com.balloon.common.util.JsonUtil;
import com.balloon.core.service.CountConfigService;
import com.balloon.count.api.admin.dto.CountConfigDto;
import com.balloon.count.api.admin.param.CountConfigParam;
import com.balloon.count.api.common.Result;
import com.balloon.top.inner.request.CountConfigReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 王思远
 * @date 2024-02-21 15:08
 */
@Slf4j
@RestController
@RequestMapping("/inner/countConfig")
public class InnerCountConfigController {

    @Autowired
    private CountConfigService countConfigService;

    @PostMapping("generateId")
    public Result<String> generateId(@RequestParam String countType){
        try {
            String countId = countConfigService.generateId(countType);
            log.info("生成计次配置id, countType={} countId={}", countType, countId);
            return Result.ofSuccess(countId);
        } catch (Throwable e) {
            log.error("计次服务-生成计次配置id异常, countType={}", countType, e);
            return ExceptionUtil.toResult(e);
        }
    }

    @PostMapping("createConfig")
    public Result<String> createConfig(@RequestBody CountConfigReq req) {
        try {
            CountConfigParam param = new CountConfigParam();
            BeanUtils.copyProperties(req, param);
            String countId = countConfigService.saveConfig(param);
            log.info("创建计次配置, req={} countId={}", JsonUtil.toJson(req), countId);
            return Result.ofSuccess(countId);
        } catch (Throwable e) {
            log.error("创建计次配置异常, param={}", JsonUtil.toJson(req), e);
            return ExceptionUtil.toResult(e);
        }
    }

    @PostMapping("queryConfig")
    public Result<CountConfigDto> queryConfig(@RequestParam String countId) {
        try {
            CountConfigDto countConfig = countConfigService.queryConfig(countId);
            log.info("查询计次配置, countId={} result={}", countId, JsonUtil.toJson(countConfig));
            return Result.ofSuccess(countConfig);
        } catch (Throwable e) {
            log.error("查询计次配置异常, countId={}", countId, e);
            return ExceptionUtil.toResult(e);
        }
    }
}
