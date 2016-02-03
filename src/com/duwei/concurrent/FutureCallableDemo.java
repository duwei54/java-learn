package com.duwei.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by duwei on 2016/1/20.
 */
public class FutureCallableDemo {
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
        ExecutorService executorService = Executors.newCachedThreadPool();


        System.out.println("提交任务:获取的斐波那契数!");
        Future<Long> future = executorService.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return fibonacci(45);
            }
        });

        try {
            System.out.println("执行其他任务!");
            Long begin = System.currentTimeMillis();
            Long result;
            result = future.get();
            Long end = System.currentTimeMillis();
            System.out.println("执行结果:" + result);
            System.out.println("耗时:" + (end - begin));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
//        while (true) {
//            try {
//                if (future.isDone()) {
//                    System.out.println("任务结果:" + future.get());
//                    break;
//                } else {
//                    System.out.println("任务还在执行中...继续等待5s");
//                    Thread.sleep(5000);
//                }
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
