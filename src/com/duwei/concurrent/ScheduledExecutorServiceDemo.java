package com.duwei.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *定时执行任务演示
 */
public class ScheduledExecutorServiceDemo {
 
    private int count; 
 
    private ScheduledExecutorService executorService;
 
    public ScheduledExecutorServiceDemo() {

        this.executorService = Executors.newScheduledThreadPool(5);
        this.executorService.scheduleWithFixedDelay(new Runnable() { 
 
            @Override
            public void run() { 
                int index = ++count; 
                System.out.println(index + " – run task …"); 
                try { 
                    Thread.sleep(1000); 
                } catch (InterruptedException e) { 
                    e.printStackTrace(); 
                } 
                System.out.println(index + " – task end."); 
            } 
        }, 1, 1, TimeUnit.MINUTES);
    } 
 
    public static void main(String[] args) { 
        new ScheduledExecutorServiceDemo();
    } 
}