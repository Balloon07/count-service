package com.balloon.common.util;

import com.balloon.common.CountException;
import com.balloon.common.enums.CountErrorEnum;
import com.balloon.count.api.common.enums.TimeUnitEnum;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Date;

/**
 * @author 王思远
 * @date 2024-02-27 22:12
 */
public class CountCycleTimeUtil {

    /**
     * 解析周期值
     *
     * @param currentTime
     * @param cycleType
     * @return
     */
    public static String parseCycleValue(Date currentTime, String cycleType) {
        if (TimeUnitEnum.life.name().equals(cycleType)) {
            return TimeUnitEnum.life.name();
        } else if (TimeUnitEnum.year.name().equals(cycleType)) {
            return DateUtil.format_yyyy(currentTime) + TimeUnitEnum.year.name();
        } else if (TimeUnitEnum.month.name().equals(cycleType)) {
            return DateUtil.format_yyyyMM(currentTime) + TimeUnitEnum.month.name();
        } else if (TimeUnitEnum.week.name().equals(cycleType)) {
            return DateUtil.format_yyyyMMdd(DateUtil.getStartTimeOfWeek(currentTime))
                    + TimeUnitEnum.week.name();
        } else if (TimeUnitEnum.day.name().equals(cycleType)) {
            return DateUtil.format_yyyyMMdd(currentTime) + TimeUnitEnum.day.name();
        } else if (TimeUnitEnum.hour.name().equals(cycleType)) {
            return DateUtil.format_yyyyMMdd_HH(currentTime) + TimeUnitEnum.hour.name();
        } else if (TimeUnitEnum.minute.name().equals(cycleType)) {
            return DateUtil.format_yyyyMMdd_HHmm(currentTime) + TimeUnitEnum.minute.name();
        } else {
            throw new CountException(CountErrorEnum.TIME_UNIT_INVALID, cycleType);
        }
    }

    /**
     * 解析当前计次周期
     *
     * @param currentTime
     * @param cycleType
     * @return
     */
    public static Pair<Date, Date> parseCurrentCycle(Date currentTime, String cycleType) {
        if (TimeUnitEnum.life.name().equals(cycleType)) {
            return Pair.of(new Date(Long.MIN_VALUE), new Date(Long.MAX_VALUE));
        } else if (TimeUnitEnum.year.name().equals(cycleType)) {
            // todo
            return DateUtil.format_yyyy(currentTime) + TimeUnitEnum.year.name();
        } else if (TimeUnitEnum.month.name().equals(cycleType)) {
            return DateUtil.format_yyyyMM(currentTime) + TimeUnitEnum.month.name();
        } else if (TimeUnitEnum.week.name().equals(cycleType)) {
            return DateUtil.format_yyyyMMdd(DateUtil.getStartTimeOfWeek(currentTime))
                    + TimeUnitEnum.week.name();
        } else if (TimeUnitEnum.day.name().equals(cycleType)) {
            return DateUtil.format_yyyyMMdd(currentTime) + TimeUnitEnum.day.name();
        } else if (TimeUnitEnum.hour.name().equals(cycleType)) {
            return DateUtil.format_yyyyMMdd_HH(currentTime) + TimeUnitEnum.hour.name();
        } else if (TimeUnitEnum.minute.name().equals(cycleType)) {
            return DateUtil.format_yyyyMMdd_HHmm(currentTime) + TimeUnitEnum.minute.name();
        } else {
            throw new CountException(CountErrorEnum.TIME_UNIT_INVALID, cycleType);
        }
    }
}
