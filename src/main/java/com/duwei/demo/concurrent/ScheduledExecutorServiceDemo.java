package com.duwei.demo.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时执行任务演示
 */
public class ScheduledExecutorServiceDemo {

    static int count = 0;
    static boolean flag = true;
    static int[] array = new int[1];

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        executorService.scheduleWithFixedDelay(() -> {
            int index = ++count;
            flag = !flag;
            if (flag) {
                try {
                    int t = array[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.printf("task-%d 抛出异常!\n", count);
                    return;
                }
            }
            System.out.println(index + " – run task …");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(index + " – task end.");
        }, 0, 5, TimeUnit.SECONDS);
    }
}