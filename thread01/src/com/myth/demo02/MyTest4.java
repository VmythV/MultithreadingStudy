package com.myth.demo02;

/**
 * synchronized是可重入锁（ReentrantLock也是可重入锁）
 * 可重入锁底层原理：计数器，当一个线程获取锁时，该计数器+1，遇到同一个锁时，也会+1，当出来时，计数器-1，所以到0时，其他线程才能获取到该锁
 */
public class MyTest4 {
    public static void main(String[] args) {
        // 可重入锁：某个线程已经获得了某个锁，允许再次获得锁，就是可重入锁
        new Thread(() -> {
            synchronized ("锁"){
                System.out.println("进入程序A");
                synchronized ("锁"){
                    System.out.println("进入程序B");
                }
            }
        }).start();
    }
}
