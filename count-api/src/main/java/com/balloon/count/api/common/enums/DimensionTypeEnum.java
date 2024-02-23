package com.balloon.count.api.common.enums;

import java.util.stream.Stream;

/**
 * 计次维度
 *
 * @author 王思远
 * @date 2023-12-14 18:03
 */
public enum DimensionTypeEnum {

    /**
     * 用户id
     */
    userId,

    /**
     * 手机号
     */
    phone,

    /**
     * 身份证
     */
    identityCard,

    /**
     * 设备号
     */
    deviceId,
    ;

    public static DimensionTypeEnum getByName(String type) {
        return Stream.of(DimensionTypeEnum.values()).filter(item -> item.name().equals(type)).findFirst().orElse(null);
    }
}
