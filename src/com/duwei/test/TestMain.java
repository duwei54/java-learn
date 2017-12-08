package com.duwei.test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by duwei on 2016/12/21.
 */
public class TestMain {

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        System.out.println(format.format(date));
    }
}
