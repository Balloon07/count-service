package com.balloon.top.biz.response;

import com.balloon.count.api.common.enums.CountStateEnum;
import com.balloon.count.api.common.enums.CycleTypeEnum;
import com.balloon.count.api.common.enums.TimeUnitEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王思远
 * @date 2024-03-11 20:45
 */
@Data
public class CountInsertResp implements Serializable {

    /**
     * 计次id
     */
    private String countId;

    /**
     * 计次维度id
     */
    private String dimensionId;

    /**
     * 状态 {@link CountStateEnum}
     */
    private String state;

    /**
     * 计次周期
     */
    private Date startTime;

    /**
     * 计次周期
     */
    private Date endTime;

    /**
     * 周期类型 {@link CycleTypeEnum}
     */
    private String cycleType;

    /**
     * 周期间隔
     * 2天3次：timeInterval=2, timeUnit=day, limitCount=3
     */
    private Integer timeInterval;

    /**
     * 周期单位 {@link TimeUnitEnum}
     */
    private String timeUnit;

    /**
     * 周期限制数量
     */
    private Integer timeLimitCount;

    /**
     * 当前计次数量
     */
    private Integer currentCount;
}
