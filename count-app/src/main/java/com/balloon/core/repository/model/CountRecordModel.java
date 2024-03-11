package com.balloon.core.repository.model;

import com.balloon.count.api.common.enums.CountTypeEnum;
import com.balloon.count.api.common.enums.DimensionTypeEnum;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王思远
 * @date 2024-03-11 15:14
 */
@Data
public class CountRecordModel {

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 请求标识（幂等号）
     */
    private String requestNo;

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
     * 计次数量
     */
    private Integer countNum;

    /**
     * 计次时间
     */
    private Date occurTime;

    /**
     * 扩展信息
     */
    private Map<String, Object> extendInfo = new HashMap<>();

}
