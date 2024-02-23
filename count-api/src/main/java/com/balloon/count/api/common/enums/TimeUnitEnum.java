package com.balloon.count.api.common.enums;

import java.util.stream.Stream;

/**
 * 周期单位
 *
 * @author 王思远
 * @date 2023-12-14 18:21
 */
public enum TimeUnitEnum {

    /**
     * 分钟
     */
    minute,

    /**
     * 小时
     */
    hour,

    /**
     * 天
     */
    day,

    /**
     * 周
     */
    week,

    /**
     * 月
     */
    month,

    /**
     * 年
     */
    year,

    /**
     * 终生
     */
    life,
    ;

    public static TimeUnitEnum getByName(String unit) {
        return Stream.of(TimeUnitEnum.values()).filter(item -> item.name().equals(unit)).findFirst().orElse(null);
    }
}
