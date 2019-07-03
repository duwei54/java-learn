package com.duwei.demo.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by duwei on 2016/1/20.
 */
public class ForkJoinTaskDemo extends RecursiveTask<Long>{
    private long n;

    public ForkJoinTaskDemo(long n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        //小于10不做分解,直接运算
        if (n <= 10) {
            return fibonacci(n);
        }
        ForkJoinTaskDemo fork1 = new ForkJoinTaskDemo(n-1);
        fork1.fork();//由ForkJoinPool分配线程来执行
        ForkJoinTaskDemo fork2 = new ForkJoinTaskDemo(n-2);
        return fork2.compute() + fork1.join();
    }
    /**
     * 求斐波那契数列
     *
     * @param n
     * @return
     */
    public static long fibonacci(long n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static void main(String[] args) {
        ForkJoinTaskDemo taskDemo = new ForkJoinTaskDemo(45);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Long begin = System.currentTimeMillis();
        Long result = forkJoinPool.invoke(taskDemo);
        Long end = System.currentTimeMillis();
        System.out.println("执行结果:" + result);
        System.out.println("耗时:" + (end - begin));

    }

}
