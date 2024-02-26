package com.balloon.top.biz;

import com.balloon.common.util.ExceptionUtil;
import com.balloon.common.util.JsonUtil;
import com.balloon.core.service.CountUserService;
import com.balloon.count.api.client.dto.CountQueryDto;
import com.balloon.count.api.client.param.CountQueryParam;
import com.balloon.count.api.common.Result;
import com.balloon.top.biz.request.CountQueryRequest;
import com.balloon.top.biz.response.CountQueryResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王思远
 * @date 2024-02-26 20:55
 */
@Slf4j
@RestController
@RequestMapping("/biz/countService")
public class BizCountServiceController {

    @Autowired
    private CountUserService countUserService;

    @PostMapping("queryCount")
    Result<CountQueryResp> queryCount(@RequestBody CountQueryRequest req) {
        try {
            CountQueryParam param = new CountQueryParam();
            BeanUtils.copyProperties(req, param);
            CountQueryDto countQueryDto = countUserService.queryCount(param);
            CountQueryResp resp = new CountQueryResp();
            BeanUtils.copyProperties(countQueryDto, resp);
            log.info("计次查询, req={} resp={}", JsonUtil.toJson(req), JsonUtil.toJson(resp));
            return Result.ofSuccess(resp);
        } catch (Throwable e) {
            log.error("计次查询, req={}", JsonUtil.toJson(req), e);
            return ExceptionUtil.toResult(e);
        }
    }


}
