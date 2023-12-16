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
