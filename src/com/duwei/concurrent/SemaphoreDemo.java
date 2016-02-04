package com.duwei.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量 演示
 * 用途:
 * 1.保护一个重要 (代码 )部分防止一次超过 N 个线程进入.
 * 2.在两个线程之间发送信号。
 * Created by duwei on 2016/2/4.
 */
public class SemaphoreDemo {

    private static final int CAR_NUM = 30;//待停车数量
    private static final int PARKING_SLOF = 10;//车位的数量

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(PARKING_SLOF, true);//设置公平 FIFO
        ExecutorService service = Executors.newCachedThreadPool();

        for (int carNo = 1; carNo <= CAR_NUM; carNo++) {
            service.execute(new Car(semaphore, carNo));
        }
        service.shutdown();
    }
}
class Car implements Runnable {

    private Semaphore semaphore;
    private Integer carNo;

    public Car(Semaphore semaphore, Integer carNo) {
        this.semaphore = semaphore;
        this.carNo = carNo;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            parking();
            Thread.sleep(1000 * (new Random()).nextInt(8));
            semaphore.release();
            leaving();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void leaving() {
        System.out.printf("%2d号车离开\n", carNo);
    }

    private void parking() {
        System.out.printf("%2d号车泊车\n", carNo);
    }
}