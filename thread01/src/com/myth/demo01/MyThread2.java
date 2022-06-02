package com.myth.demo01;

/**
 * 线程实现方法二  实现Runable接口
 */
public class MyThread2 {
    public static void main(String[] args) {

        // 实例化对象
        MyRunnable myRunnable1 = new MyRunnable();
        MyRunnable myRunnable2 = new MyRunnable();

        Thread thread1 = new Thread(myRunnable1, "AAA");
        Thread thread2 = new Thread(myRunnable1, "BBB");

        thread1.start();
        thread2.start();

        // =============================
        // 使用匿名内部类
        // new Thread ，调用
        Thread thread11 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 方法体
            }
        }, "AAA");
        thread11.start();

        // 直接.start
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 方法体
            }
        }, "AAA").start();

        // 使用lambda表达式
        new Thread(() -> {
            // 方法体
        }, "AAA").start();
    }
}

class MyRunnable implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName()+"运行了"+i+"次");
        }
    }
}
