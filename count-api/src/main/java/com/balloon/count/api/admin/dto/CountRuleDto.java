package com.balloon.count.api.admin.dto;

import com.balloon.count.api.common.enums.CycleTypeEnum;
import com.balloon.count.api.common.enums.TimeUnitEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 王思远
 * @date 2024-02-23 17:25
 */
@Data
public class CountRuleDto implements Serializable {

    /**
     * 周期类型 {@link CycleTypeEnum}
     */
    private String cycleType;

    /**
     * 周期间隔
     */
    private Integer timeInterval;

    /**
     * 周期单位 {@link TimeUnitEnum}
     */
    private String timeUnit;

    /**
     * 周期次数
     */
    private Integer timeTotal;
}
