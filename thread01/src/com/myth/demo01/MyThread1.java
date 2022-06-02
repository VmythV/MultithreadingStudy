package com.myth.demo01;

/**
 * 线程实现方法一  继承Thread类
 */
public class MyThread1 {
    public static void main(String[] args) {

        // 实例化对象
        ThreadTest threadTest1 = new ThreadTest();
        // 设置线程名称
        threadTest1.setName("AAA");
        // 开启多线程
        threadTest1.start();

        ThreadTest threadTest2 = new ThreadTest();
        threadTest2.setName("BBB");
        threadTest2.start();


    }
}

class ThreadTest extends Thread{
    @Override
    public void run() {
        // 获取当前线程的名称
        for (int i = 1; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName()+"运行了"+i+"次");
        }
    }
}
