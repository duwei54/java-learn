package com.duwei.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 多消费者多生产者  阻塞队列实现
 * Created by duwei on 2016/2/4.
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> store = new ArrayBlockingQueue<>(3);
        BQConsumer consumer = new BQConsumer(store);
        BQConsumer consumer1 = new BQConsumer(store);
        BQConsumer consumer2 = new BQConsumer(store);
        BQConsumer consumer3 = new BQConsumer(store);
        BQProducer producer = new BQProducer(store);
        BQProducer producer1 = new BQProducer(store);
        BQProducer producer2 = new BQProducer(store);
        BQProducer producer3 = new BQProducer(store);

        consumer.start();
//        consumer1.start();
//		consumer2.start();
//		consumer3.start();

        producer.start();
        producer1.start();
        producer2.start();
//        producer3.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        System.exit(0);
    }
}

class BQProducer extends Thread {
    private BlockingQueue store = null;

    public BQProducer(BlockingQueue store) {
        this.store = store;
    }

    @Override
    public void run() {

        while (true) {
            try {
                String productName = Thread.currentThread().getName();
                store.put(productName);
//                System.out.printf("---生产者[%s]生产产品,仓库库存为[%s]---\n", productName, store.size());//此处无法控制结束阻断后执行顺序
                Thread.sleep(110);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class BQConsumer extends Thread {
    private BlockingQueue store = null;

    public BQConsumer(BlockingQueue store) {
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String product = (String) store.take();
//                System.out.printf("---消费者[%s]  取出  [%s]产品,仓库库存为[%s]---\n", Thread.currentThread().getName(), product, store.size());//此处无法控制结束阻断后执行顺序
                Thread.sleep(110);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
