package com.balloon.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 王思远
 * @date 2024-02-27 21:30
 */
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
        return yyyy.format(date);
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

    public static Date getStartTimeOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY); // 设置星期一为一周的第一天
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 设置为星期一
        return calendar.getTime();
    }
}
