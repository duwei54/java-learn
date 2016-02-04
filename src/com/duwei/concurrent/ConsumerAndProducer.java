package com.duwei.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多生产者多消费者(concurrent lock 实现) 演示
 */
public class ConsumerAndProducer {

    public static void main(String[] args) {

        Store store = new Store(0);
        Consumer consumer = new Consumer(store);
        Consumer consumer1 = new Consumer(store);
        Consumer consumer2 = new Consumer(store);
        Consumer consumer3 = new Consumer(store);
        Producer producer = new Producer(store);
        Producer producer1 = new Producer(store);
        Producer producer2 = new Producer(store);
        Producer producer3 = new Producer(store);

        consumer.start();
        consumer1.start();
//		consumer2.start();
//		consumer3.start();

        producer.start();
        producer1.start();
//        producer2.start();
//        producer3.start();
//        producer3.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        System.exit(0);
    }
}

class Producer extends Thread {
    private Store store = null;

    public Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {

        while (true) {
            store.addin();
            try {
                Thread.sleep(110);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
    private Store store = null;

    public Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            store.getout();
            try {
                Thread.sleep(110);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Store {
    private static final int MAX = 3;// 最大库存量
    private Lock lock = new ReentrantLock();
//    private Condition condition = lock.newCondition();
    private Condition consumerCondition = lock.newCondition();//消费者等待集合
    private Condition producerCondition = lock.newCondition();//消费者等待集合

    private int num;
    public boolean flag = false; //多生产者多消费者模式


    public Store(int num) {
        this.num = num;
    }

    public void getout() {
        try {
            lock.lock();
            System.out.println("----------------------------");
            System.out.println("准备取货,库存量:" + this.num);
            if (this.num <= 0) {
                System.out.println("库存不足，等待补货！");
                System.out.println("当前等待为线程：" + Thread.currentThread().getId());
                while (!flag) {//用while 确保线程醒了后再次，判断标记。
                    try {
                        consumerCondition.await();//加入消费者等待集合
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            this.num--;
            System.out.println("取货成功,库存量:" + this.num);
            System.out.println("当前取货为线程：" + Thread.currentThread().getId());
            System.out.println("----------------------------");

            this.flag = false;

            producerCondition.signal();//通知生产者
        }finally {
            lock.unlock();
        }
    }

    public void addin() {
        try {
            lock.lock();
            System.out.println("****************************");
            System.out.println("准备补货,库存量:" + this.num);
            if (this.num == MAX) {
                System.out.println("仓库已满，等待出货！");
                System.out.println("当前等待为线程：" + Thread.currentThread().getId());
                while (flag) {//用while 确保线程醒了后再次，判断标记。
                    try {
                        producerCondition.await();//加入生产者等待集合
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            this.num++;
            System.out.println("补货成功,库存量:" + this.num);
            System.out.println("当前补货为线程：" + Thread.currentThread().getId());
            System.out.println("****************************");
            this.flag = true;

            consumerCondition.signal();//通知消费者
        }finally {
            lock.unlock();
        }
    }
}
