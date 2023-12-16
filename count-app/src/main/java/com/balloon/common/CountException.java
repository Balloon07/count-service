package com.balloon.common;

import com.balloon.common.enums.CountErrorEnum;
import lombok.Data;

/**
 * @author 王思远
 * @date 2023-12-16 19:15
 */
@Data
public class CountException extends RuntimeException {

    private CountErrorEnum error;


}
