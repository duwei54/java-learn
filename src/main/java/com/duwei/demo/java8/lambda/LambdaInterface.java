package com.duwei.demo.java8.lambda;

/**Lambda 测试接口
 * Created by duwei on 2016/11/25.
 */
@FunctionalInterface
public interface LambdaInterface {
    String hello();
    default String world(){
        return "World!";
    }
}
