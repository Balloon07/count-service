package com.balloon.common.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 王思远
 * @date 2024-02-27 21:30
 */
@Slf4j
public class DateUtil {

    private static final SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
    private static final SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyyMM");
    private static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat yyyyMMdd_HH = new SimpleDateFormat("yyyyMMdd_HH");
    private static final SimpleDateFormat yyyyMMdd_HHmm = new SimpleDateFormat("yyyyMMdd_HHmm");

    public static String format_yyyy(Date date) {
        if (date == null) {
            return null;
        }
        return yyyy.format(date);
    }

    public static String format_yyyyMM(Date date) {
        if (date == null) {
            return null;
        }
        return yyyyMM.format(date);
    }

    public static String format_yyyyMMdd(Date date) {
        if (date == null) {
            return null;
        }
        return yyyyMMdd.format(date);
    }

    public static String format_yyyyMMdd_HH(Date date) {
        if (date == null) {
            return null;
        }
        return yyyyMMdd_HH.format(date);
    }

    public static String format_yyyyMMdd_HHmm(Date date) {
        if (date == null) {
            return null;
        }
        return yyyyMMdd_HHmm.format(date);
    }

    public static Date getStartTimeOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 设置为当年的1月1日
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        // 设置时分秒毫秒为0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndTimeOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 设置为当年的12月31日
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        // 设置时分秒毫秒为最后时刻
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date getStartTimeOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 设置为当月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        // 设置时分秒毫秒为0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndTimeOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 设置为下个月的第一天，然后回退一天即为当月的最后一天
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        // 设置时分秒毫秒为最后时刻
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date getStartTimeOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 设置为本周的周一
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        // 设置时分秒毫秒为0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndTimeOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 设置为下周的周一，然后回退一天即为当前周的周日
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        // 设置时分秒毫秒为最后时刻
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date getStartTimeOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 设置时分秒毫秒为0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndTimeOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 设置时分秒毫秒为最后时刻
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date getStartTimeOfHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 设置分秒毫秒为0
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndTimeOfHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 设置分秒毫秒为最后时刻
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date getStartTimeOfMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 设置秒毫秒为0
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndTimeOfMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 设置秒毫秒为最后时刻
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        Date now = new Date();
        log.info("nowTime={}", JsonUtil.toJson(now));
        log.info("---------------------------------");
        Date startTimeOfYear = getStartTimeOfYear(now);
        Date endTimeOfYear = getEndTimeOfYear(now);
        log.info("startTimeOfYear={}", JsonUtil.toJson(startTimeOfYear));
        log.info("endTimeOfYear={}", JsonUtil.toJson(endTimeOfYear));
        log.info("---------------------------------");
        Date startTimeOfMonth = getStartTimeOfMonth(now);
        Date endTimeOfMonth = getEndTimeOfMonth(now);
        log.info("startTimeOfMonth={}", JsonUtil.toJson(startTimeOfMonth));
        log.info("endTimeOfMonth={}", JsonUtil.toJson(endTimeOfMonth));
        log.info("---------------------------------");
        Date startTimeOfWeek = getStartTimeOfWeek(now);
        Date endTimeOfWeek = getEndTimeOfWeek(now);
        log.info("startTimeOfWeek={}", JsonUtil.toJson(startTimeOfWeek));
        log.info("endTimeOfWeek={}", JsonUtil.toJson(endTimeOfWeek));
        log.info("---------------------------------");
        Date startTimeOfDay = getStartTimeOfDay(now);
        Date endTimeOfDay= getEndTimeOfDay(now);
        log.info("startTimeOfDay={}", JsonUtil.toJson(startTimeOfDay));
        log.info("endTimeOfDay={}", JsonUtil.toJson(endTimeOfDay));
        log.info("---------------------------------");
        Date startTimeOfHour = getStartTimeOfHour(now);
        Date endTimeOfHour= getEndTimeOfHour(now);
        log.info("startTimeOfHour={}", JsonUtil.toJson(startTimeOfHour));
        log.info("endTimeOfHour={}", JsonUtil.toJson(endTimeOfHour));
        log.info("---------------------------------");
        Date startTimeOfMinute = getStartTimeOfMinute(now);
        Date endTimeOfMinute= getEndTimeOfMinute(now);
        log.info("startTimeOfMinute={}", JsonUtil.toJson(startTimeOfMinute));
        log.info("endTimeOfMinute={}", JsonUtil.toJson(endTimeOfMinute));
        log.info("---------------------------------");
    }
}
