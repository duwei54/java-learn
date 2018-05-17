package com.duwei.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 相对时间格式化
 *
 * @author duwei
 */
public class RelativeDateFormat {

    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;

    private static final String SECOND = " 秒";
    private static final String MINUTE = " 分 ";
    private static final String HOUR = " 小时 ";
    private static final String DAY = " 天 ";

    public static String getDuration(Date date) {

        if (null == date) {
            return "0" + SECOND;
        }
        String result = "";
        long duration = System.currentTimeMillis() - date.getTime();

        if (duration <= 0) {
            return "0" + SECOND;
        }
        boolean prefixFlg = false;
        long days = toDays(duration);
        if (days > 0) {
            prefixFlg = true;
            result += days + DAY;
            duration -= days * ONE_DAY;
        }

        long hours = toHours(duration);
        if (hours > 0 || prefixFlg) {
            prefixFlg = true;
            result += hours + HOUR;
            duration -= hours * ONE_HOUR;
        }

        long minutes = toMinutes(duration);
        if (minutes > 0 || prefixFlg) {
            prefixFlg = true;
            result += minutes + MINUTE;
            duration -= minutes * ONE_MINUTE;
        }

        long seconds = toSeconds(duration);
        if (seconds > 0 || prefixFlg) {
            result += seconds + SECOND;
        }
        return result;
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
        Date date = format.parse("2018-05-07 11:03:01");
        System.out.println(getDuration(date));
    }

}