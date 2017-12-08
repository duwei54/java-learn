package com.duwei.java8.lambda;

import java.util.Arrays;
import java.util.Collection;

/**
 * Lambda 测试类
 * Created by duwei on 2016/11/25.
 */
public class LambdaMainTest {

    public static void main(String[] args) {
        Collection<String> collection = Arrays.asList("ab", "bc", "de");
        collection.forEach(System.out::println);
        long s = collection.stream().filter(item -> item.contains("b")).count();
        System.out.println(s);
    }
}
