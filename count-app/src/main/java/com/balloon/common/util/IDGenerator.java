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

    private static String random(int len) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, len);
    }

}
