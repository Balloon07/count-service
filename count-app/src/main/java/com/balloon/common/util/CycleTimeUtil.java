package com.balloon.common.util;

import com.balloon.common.CountException;
import com.balloon.common.enums.CountErrorEnum;
import com.balloon.core.repository.model.CountRuleModel;
import com.balloon.count.api.common.enums.CycleTypeEnum;
import com.balloon.count.api.common.enums.TimeUnitEnum;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Date;

/**
 * @author 王思远
 * @date 2024-02-27 22:12
 */
public class CycleTimeUtil {

    /**
     * 解析周期值
     *
     * @param currentTime
     * @param timeUnit
     * @return
     */
    public static String parseCycleValue(Date currentTime, String timeUnit) {
        if (TimeUnitEnum.life.name().equals(timeUnit)) {
            return TimeUnitEnum.life.name();
        } else if (TimeUnitEnum.year.name().equals(timeUnit)) {
            return DateUtil.format_yyyy(currentTime) + TimeUnitEnum.year.name();
        } else if (TimeUnitEnum.month.name().equals(timeUnit)) {
            return DateUtil.format_yyyyMM(currentTime) + TimeUnitEnum.month.name();
        } else if (TimeUnitEnum.week.name().equals(timeUnit)) {
            return DateUtil.format_yyyyMMdd(DateUtil.getStartTimeOfWeek(currentTime))
                    + TimeUnitEnum.week.name();
        } else if (TimeUnitEnum.day.name().equals(timeUnit)) {
            return DateUtil.format_yyyyMMdd(currentTime) + TimeUnitEnum.day.name();
        } else if (TimeUnitEnum.hour.name().equals(timeUnit)) {
            return DateUtil.format_yyyyMMdd_HH(currentTime) + TimeUnitEnum.hour.name();
        } else if (TimeUnitEnum.minute.name().equals(timeUnit)) {
            return DateUtil.format_yyyyMMdd_HHmm(currentTime) + TimeUnitEnum.minute.name();
        } else {
            throw new CountException(CountErrorEnum.TIME_UNIT_INVALID, timeUnit);
        }
    }

    /**
     * 解析当前计次周期
     *
     * @param currentTime
     * @param countRule
     * @return
     */
    public static Pair<Date, Date> parseCurrentCycle(Date currentTime, CountRuleModel countRule) {
        String cycleType = countRule.getCycleType();
        Integer timeInterval = countRule.getTimeInterval();
        String timeUnit = countRule.getTimeUnit();
        if (TimeUnitEnum.life.name().equals(cycleType)) {
            // 终生
            return Pair.of(new Date(Long.MIN_VALUE), new Date(Long.MAX_VALUE));
        }
        if (CycleTypeEnum.natural.name().equals(cycleType)) {
            // 相对周期
            if (TimeUnitEnum.year.name().equals(timeUnit)) {
                return Pair.of(DateUtils.addYears(currentTime, -timeInterval), currentTime);
            } else if (TimeUnitEnum.month.name().equals(timeUnit)) {
                return Pair.of(DateUtils.addMonths(currentTime, -timeInterval), currentTime);
            } else if (TimeUnitEnum.week.name().equals(timeUnit)) {
                return Pair.of(DateUtils.addWeeks(currentTime, -timeInterval), currentTime);
            } else if (TimeUnitEnum.day.name().equals(timeUnit)) {
                return Pair.of(DateUtils.addDays(currentTime, -timeInterval), currentTime);
            } else if (TimeUnitEnum.hour.name().equals(timeUnit)) {
                return Pair.of(DateUtils.addHours(currentTime, -timeInterval), currentTime);
            } else if (TimeUnitEnum.minute.name().equals(timeUnit)) {
                return Pair.of(DateUtils.addMinutes(currentTime, -timeInterval), currentTime);
            } else {
                throw new CountException(CountErrorEnum.TIME_UNIT_INVALID, cycleType);
            }
        } else {
            // 自然周期/绝对周期
            if (TimeUnitEnum.year.name().equals(timeUnit)) {
                return Pair.of(DateUtils.addYears(DateUtil.getStartTimeOfYear(currentTime), 1 - timeInterval), DateUtil.getEndTimeOfYear(currentTime));
            } else if (TimeUnitEnum.month.name().equals(timeUnit)) {
                return Pair.of(DateUtils.addMonths(DateUtil.getStartTimeOfMonth(currentTime), 1 - timeInterval), DateUtil.getEndTimeOfMonth(currentTime));
            } else if (TimeUnitEnum.week.name().equals(timeUnit)) {
                return Pair.of(DateUtils.addWeeks(DateUtil.getStartTimeOfWeek(currentTime), 1 - timeInterval), DateUtil.getEndTimeOfWeek(currentTime));
            } else if (TimeUnitEnum.day.name().equals(timeUnit)) {
                return Pair.of(DateUtils.addDays(DateUtil.getStartTimeOfDay(currentTime), 1 - timeInterval), DateUtil.getEndTimeOfDay(currentTime));
            } else if (TimeUnitEnum.hour.name().equals(timeUnit)) {
                return Pair.of(DateUtils.addHours(DateUtil.getStartTimeOfHour(currentTime), 1 - timeInterval), DateUtil.getEndTimeOfHour(currentTime));
            } else if (TimeUnitEnum.minute.name().equals(timeUnit)) {
                return Pair.of(DateUtils.addMinutes(DateUtil.getStartTimeOfMinute(currentTime), 1 - timeInterval), DateUtil.getEndTimeOfMinute(currentTime));
            } else {
                throw new CountException(CountErrorEnum.TIME_UNIT_INVALID, timeUnit);
            }
        }
    }
}
