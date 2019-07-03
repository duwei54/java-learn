package com.duwei.demo.designpattern.singleton;

/**
 * 单例模式(double-checked) 示例
 * @author duwei on 2016/1/20.
 * @since JDK 1.5
 */
public class SingletonDemo {

    /**
     * 使用volatile修饰符,保持instance内存可见性
     */
    private volatile static SingletonDemo instance;

    private SingletonDemo() {

    }

    public static SingletonDemo getInstance() {
        if (null == instance) {
            //instance为空的时候进入同步区块
            synchronized (SingletonDemo.class) {
                //只有第一次彻底执行才会进入
                if (null == instance) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public void demoMethod(){
        System.out.printf("线程[%s]调用单例[%s]的方法!\n",Thread.currentThread().getId(),instance);
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            SingletonDemo singleTon = SingletonDemo.getInstance();
            singleTon.demoMethod();
        });
        Thread t2 = new Thread(() -> {
            SingletonDemo singleTon = SingletonDemo.getInstance();
            singleTon.demoMethod();
        });

        t1.start();
        t2.start();
    }
}
