package com.balloon.core.repository.model;

import com.balloon.count.api.common.enums.CycleTypeEnum;
import com.balloon.count.api.common.enums.TimeUnitEnum;
import lombok.Data;

/**
 * 计次规则
 * <p>
 * 1.2天3次，自然日24小时记一天：cycleType=natural, timeInterval=2,timeUnit=day, timeTotal=3 <p>
 * 2.1周3次，领取后7天记一周：cycleType=relative, timeInterval=1,timeUnit=week, timeTotal=3 <p>
 * 3.终生一次or活动周期内一次：cycleType=life, timeTotal=1 <p>
 *
 * @author 王思远
 * @date 2023-12-18 01:57
 */
@Data
public class CountRuleModel {

    /**
     * 周期类型 {@link CycleTypeEnum}
     */
    private String cycleType;

    /**
     * 周期间隔
     */
    private Integer timeInterval;

    /**
     * 周期单位 {@link TimeUnitEnum}
     */
    private String timeUnit;

    /**
     * 周期次数
     */
    private Integer timeTotal;
}
