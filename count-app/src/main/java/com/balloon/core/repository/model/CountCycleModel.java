package com.balloon.core.repository.model;

import com.balloon.count.api.common.enums.CountTypeEnum;
import com.balloon.count.api.common.enums.DimensionTypeEnum;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王思远
 * @date 2024-02-27 16:30
 */
@Data
public class CountCycleModel {
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 计次维度 {@link DimensionTypeEnum}
     */
    private String dimensionType;

    /**
     * 计次维度id
     */
    private String dimensionId;

    /**
     * 计次id
     */
    private String countId;

    /**
     * 计次类型 {@link CountTypeEnum}
     */
    private String countType;

    /**
     * 计次周期信息
     */
    private CycleInfoModel cycleInfo;

    /**
     * 扩展信息
     */
    private Map<String, Object> extendInfo = new HashMap<>();

}
