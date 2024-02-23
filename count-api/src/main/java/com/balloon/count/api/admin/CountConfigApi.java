package com.balloon.count.api.admin;

import com.balloon.count.api.admin.dto.CountConfigDto;
import com.balloon.count.api.admin.param.CountConfigParam;
import com.balloon.count.api.common.Result;

/**
 * 计次配置api
 *
 * @author 王思远
 * @date 2023-12-16 16:50
 */
public interface CountConfigApi {

    /**
     * 生成计次配置id
     *
     * @param countType
     * @return
     */
    Result<String> generateCountId(String countType);

    /**
     * 创建计次配置
     *
     * @param param
     * @return
     */
    Result<String> createCountConfig(CountConfigParam param);

    /**
     * 查询计次配置
     *
     * @param countId
     * @return
     */
    Result<CountConfigDto> queryCountConfig(String countId);

}
