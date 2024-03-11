package com.balloon.count.api.client.param;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王思远
 * @date 2024-02-26 20:40
 */
@Data
public class CountInsertParam implements Serializable {

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
