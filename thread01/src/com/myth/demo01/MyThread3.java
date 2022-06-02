package com.myth.demo01;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现Callable接口 定义线程
 */
public class MyThread3 {

    public static void main(String[] args) {
        // 实例化Callable对象
        MyCallable myCallable = new MyCallable();
        // 使用FutureTask类来包装Callable对象
        FutureTask<Integer> futureTask1 = new FutureTask<>(myCallable);
        FutureTask<Integer> futureTask2 = new FutureTask<>(myCallable);
        // 开启多线程
        Thread thread1 = new Thread(futureTask1);
        thread1.start();

        Thread thread2 = new Thread(futureTask2);
        thread2.start();

        // 获取线程结束后的返回值
        try {
            Integer num1 = futureTask1.get();
            System.out.println(thread1.getName()+"==="+num1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Integer num2 = futureTask2.get();
            System.out.println(thread2.getName()+"==="+num2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class MyCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int num = new Random().nextInt(10);
        System.out.println(Thread.currentThread().getName()+"---"+ num);
        return num;
    }
}