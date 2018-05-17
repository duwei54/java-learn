package com.duwei.java8;

/**
 * 默认方法测试
 * Created by duwei on 2017/5/20.
 */
public class DefaultMethod {

    public static void main(String args[]) {
        Younger younger = new Student();
        younger.print();
    }
}

interface Younger {
    default void print() {
        System.out.println("I am a younger.");
    }

    static void sayHi() {
        System.out.println("Young is the capital.");
    }
}

interface Learner {
    default void print() {
        System.out.println("I am a learner.");
    }
}

class Student implements Younger, Learner {
    public void print() {
        Younger.super.print();
        Learner.super.print();
        Younger.sayHi();
        System.out.println("I am a student!");
    }
}