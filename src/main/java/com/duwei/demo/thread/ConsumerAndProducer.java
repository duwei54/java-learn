package com.duwei.demo.thread;

/**
 * 多生产者多消费者 演示
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
        producer2.start();
        producer3.start();

        try {
            Thread.sleep(3000);
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
                Thread.sleep(100);
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
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Store {
    private int MAX = 3;// 最大库存量
    private int num;
    public boolean flag = false; //多生产者多消费者模式


    public Store(int num) {
        this.num = num;
    }

    public synchronized void getout() {
        System.out.println("----------------------------");
        System.out.println("准备取货,库存量:" + this.num);
        if (this.num == 0) {
            System.out.println("库存不足，等待补货！");
            System.out.println("当前等待为线程：" + Thread.currentThread().getId());
            while (!flag) {
                try {
                    this.wait();
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
        notify();
    }

    public synchronized void addin() {

        System.out.println("****************************");
        System.out.println("准备补货,库存量:" + this.num);
        if (this.num == MAX) {
            System.out.println("仓库已满，等待出货！");
            System.out.println("当前等待为线程：" + Thread.currentThread().getId());
            while (flag) {
                try {
                    wait();
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
        notify();
    }
}
