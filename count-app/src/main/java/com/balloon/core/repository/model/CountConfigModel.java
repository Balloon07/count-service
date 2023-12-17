package com.balloon.core.repository.model;

import com.balloon.count.api.common.enums.CountStateEnum;
import com.balloon.count.api.common.enums.CountTypeEnum;
import com.balloon.count.api.common.enums.DimensionTypeEnum;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author 王思远
 * @date 2023-12-17 03:22
 */
@Data
public class CountConfigModel {

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人
     */
    private String updater;

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
     * 计次规则
     */
    private CountRuleModel countRule;

    /**
     * 扩展信息
     */
    private Map<String, Object> extendInfo;

}
