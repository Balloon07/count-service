package com.balloon.common;

import com.balloon.common.enums.CountErrorEnum;
import lombok.Getter;

/**
 * @author 王思远
 * @date 2023-12-16 19:15
 */
public class CountException extends RuntimeException {

    @Getter
    private final long code;

    public CountException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CountException(CountErrorEnum error) {
        super(error.getErrorMsg());
        this.code = error.getErrorCode();
    }

}
