package com.balloon.top.biz.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 王思远
 * @date 2024-02-26 21:00
 */
@Data
public class CountQueryRequest implements Serializable {

    /**
     * 计次id
     */
    private String countId;

    /**
     * 计次维度id
     */
    private String dimensionId;
}
