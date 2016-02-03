package com.duwei.designpattern.singleton;

/**
 * 单例模式(double-checked) 示例
 * Created by duwei on 2016/1/20.
 * @since JDK 1.5
 */
public class SingleTonDemo {

    private volatile static SingleTonDemo instance;//使用volatile修饰符,保持instance内存可见性

    private SingleTonDemo() {

    }

    public static SingleTonDemo getInstance() {
        if (null == instance) {//instance为空的时候进入同步区块
            synchronized (SingleTonDemo.class) {//只有第一次彻底执行才会进入
                if (null == instance) {
                    instance = new SingleTonDemo();
                }
            }
        }
        return instance;
    }

    public void demoMethod(){
        System.out.printf("线程[%s]调用单例[%s]的方法!\n",Thread.currentThread().getId(),instance);
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                SingleTonDemo singleTon = SingleTonDemo.getInstance();
                singleTon.demoMethod();
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                SingleTonDemo singleTon = SingleTonDemo.getInstance();
                singleTon.demoMethod();
            }
        };

        t1.start();
        t2.start();
    }
}
