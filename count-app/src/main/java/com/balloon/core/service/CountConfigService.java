package com.balloon.core.service;

import com.balloon.count.api.admin.dto.CountConfigDto;
import com.balloon.count.api.admin.param.CountConfigParam;

/**
 * @author 王思远
 * @date 2023-12-16 18:40
 */
public interface CountConfigService {

    /**
     * 生成计次配置id
     *
     * @param countType
     * @return
     */
    String generateId(String countType);

    /**
     * 创建计次配置
     *
     * @param param
     * @return
     */
    String saveConfig(CountConfigParam param);

    /**
     * 查询计次配置
     *
     * @param countId
     * @return
     */
    CountConfigDto queryConfig(String countId);
}
