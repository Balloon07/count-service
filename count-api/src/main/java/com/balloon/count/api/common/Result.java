package com.balloon.count.api.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 王思远
 * @date 2023-12-14 18:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    public static final Long SUCCESS_CODE = 200L;
    public static final String SUCCESS_MSG = "success";

    /**
     * 响应码
     */
    private long code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;


    public static <T> Result<T> ofFailure(Long code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static <T> Result<T> ofSuccess(T data) {
        return new Result<>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static boolean isSuccess(Result result) {
        return result != null && SUCCESS_CODE.equals(result.getCode());
    }
}
