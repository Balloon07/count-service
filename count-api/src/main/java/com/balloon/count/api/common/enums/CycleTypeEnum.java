package com.balloon.count.api.common.enums;

import java.util.stream.Stream;

/**
 * 周期类型
 *
 * @author 王思远
 * @date 2023-12-14 18:18
 */
public enum CycleTypeEnum {

    /**
     * 自然周期/绝对周期
     */
    natural,

    /**
     * 相对周期
     */
    relative,
    ;

    public static CycleTypeEnum getByName(String type) {
        return Stream.of(CycleTypeEnum.values()).filter(item -> item.name().equals(type)).findFirst().orElse(null);
    }
}
