package com.balloon.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author 王思远
 * @date 2023-12-17 00:59
 */
public class IDGenerator {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public static String generateId() {
        return dateFormat.format(new Date()) + random(8);
    }

    public static String generateUUID() {
        return random(null);
    }

    private static String random(Integer len) {
        String str = UUID.randomUUID().toString().replace("-", "");
        if (len != null) {
            return str.substring(0, len);
        }
        return str;
    }

}
