package com.myth.demo03;

/**
 * 可见性案例
 */
public class TestA {

    // 不加volatile，flag就不是可见的
    // public static boolean flag = true;
    // 加上volatile，flag就是可见的
    public static volatile boolean flag = true;

    public static void main(String[] args) {

        new Thread(() -> {
            while (flag){
                System.out.println("flag 为true");
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = false;
    }
}
