package com.balloon.count.api.client.param;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王思远
 * @date 2024-02-26 15:47
 */
@Data
public class CountQueryParam implements Serializable {

    /**
     * 计次id
     */
    private String countId;

    /**
     * 计次维度id
     */
    private String dimensionId;

    /**
     * 时间戳
     */
    private Date occurTime;

}
