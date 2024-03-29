package com.balloon.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 王思远
 * @date 2023-12-16 19:07
 */
@Getter
@AllArgsConstructor
public enum CountErrorEnum {

    SYSTEM_ERROR(5000L, "服务异常"),
    PARAM_ERROR(5001L, "参数异常"),
    CONFIG_NOT_FUND(5002L, "计次配置不存在，countId=%s"),
    CONFIG_NOT_GOING_STATE(5002L, "计次配置不是going状态，countId=%s"),
    TIME_UNIT_INVALID(5003L, "计次周期单位不合法，timeUnit=%s"),
    BUSY_OPERATION(5004L, "操作繁忙，请稍后再试"),
    COUNT_EXCEED_LIMIT(5005L, "超出当前周期计次限制，countId=%s, dimensionId=%s"),
    COUNT_INSERT_IDEMPOTENT(5006L, "计次准入幂等，countId=%s, dimensionId=%s"),
    ;

    /**
     * 计次异常码
     */
    private long errorCode;

    /**
     * 计次异常信息
     */
    private String errorMsg;

}
