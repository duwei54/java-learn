package com.duwei.java8.date;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 时区测试
 * @author duwei 2017/5/20.
 */
public class TimeZoneTest {
    public static void main(String args[]){
        TimeZoneTest tester = new TimeZoneTest();
        tester.testZonedDateTime();
    }

    public void testZonedDateTime(){

        // 将字符串代表的时区信息转化
        ZonedDateTime date1 = ZonedDateTime.parse("2016-04-20T19:22:15+01:30[Europe/Paris]");
        System.out.println("date1: " + date1);

        // 设定地区ID为亚洲的加尔各答（位于印度），并输出
        ZoneId id = ZoneId.of("Asia/Kolkata");
        System.out.println("ZoneId: " + id);

        // 获得系统默认的当前地区并输出
        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("CurrentZone: " + currentZone);
    }
}
