package com.balloon.top.biz.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王思远
 * @date 2024-03-11 20:43
 */
@Data
public class CountInsertRequest implements Serializable {

    /**
     * 计次id
     */
    private String countId;

    /**
     * 计次维度id
     */
    private String dimensionId;

    /**
     * 计次数量
     */
    private Integer countNum;

    /**
     * 请求标识（幂等号）
     */
    private String requestNo;

    /**
     * 时间戳
     */
    private Date occurTime;
}
