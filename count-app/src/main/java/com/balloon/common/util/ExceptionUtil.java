package com.balloon.common.util;

import com.balloon.common.CountException;
import com.balloon.common.enums.CountErrorEnum;
import com.balloon.count.api.common.Result;

/**
 * @author 王思远
 * @date 2023-12-16 19:21
 */
public class ExceptionUtil {

    public static Result toResult(Throwable e) {
        Result result = new Result();
        if (e instanceof CountException) {
            CountErrorEnum error = ((CountException) e).getError();
            result.setCode(error.getErrorCode());
            result.setMsg(error.getErrorMsg());
        } else if (e instanceof IllegalArgumentException) {
            result.setCode(CountErrorEnum.PARAM_ERROR.getErrorCode());
            result.setMsg(CountErrorEnum.PARAM_ERROR.getErrorMsg() + "-" + e.getMessage());
        } else {
            result.setCode(CountErrorEnum.SYSTEM_ERROR.getErrorCode());
            result.setMsg(CountErrorEnum.SYSTEM_ERROR.getErrorMsg());
        }
        return result;
    }
}
