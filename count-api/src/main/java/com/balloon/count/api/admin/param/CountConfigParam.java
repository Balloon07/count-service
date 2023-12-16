package com.balloon.count.api.admin.param;

import com.balloon.count.api.common.enums.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 计次配置 <p>
 * 1.2天3次，自然日24小时记一天：cycleType=natural, timeInterval=2,timeUnit=day, timeTotal=3 <p>
 * 2.1周3次，领取后7天记一周：cycleType=relative, timeInterval=1,timeUnit=week, timeTotal=3 <p>
 * 3.终生一次or活动周期内一次：cycleType=life, timeTotal=1 <p>
 *
 * @author 王思远
 * @date 2023-12-16 17:41
 */
@Data
public class CountConfigParam implements Serializable {

    /**
     * 计次id
     */
    private String countId;

    /**
     * 计次类型 {@link CountTypeEnum}
     */
    private String countType;

    /**
     * 计次名称
     */
    private String countName;

    /**
     * 计次维度 {@link DimensionTypeEnum}
     */
    private String dimensionType;

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

    /**
     * 扩展信息
     */
    private Map<String, Object> extendInfo;

    /**
     * 操作人
     */
    private String operator;
}
