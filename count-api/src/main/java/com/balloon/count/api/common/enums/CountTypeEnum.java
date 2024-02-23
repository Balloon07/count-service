package com.balloon.count.api.common.enums;

import java.util.stream.Stream;

/**
 * 计次类型
 *
 * @author 王思远
 * @date 2023-12-14 18:15
 */
public enum CountTypeEnum {

    /**
     * 活动
     */
    activity,

    /**
     * 奖品
     */
    prize,

    /**
     * 任务
     */
    task;

    public static CountTypeEnum getByName(String type) {
        return Stream.of(CountTypeEnum.values()).filter(item -> item.name().equals(type)).findFirst().orElse(null);
    }

}
