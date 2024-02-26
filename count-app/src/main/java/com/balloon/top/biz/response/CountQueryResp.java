package com.balloon.top.biz.response;

import com.balloon.count.api.common.enums.CountStateEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王思远
 * @date 2024-02-26 21:01
 */
public class CountQueryResp implements Serializable {

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
     * 计次限制数量
     */
    private Integer limitCount;

    /**
     * 当前计次数量
     */
    private Integer currentCount;

}
