package com.myth.demo03;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子性
 */
public class TestC {
    /**
     * 不能保证原子性
      */
    //static int num = 0;
    /**
     * AtomicInteger类，原子性Integer类，底层就是用了volatile+CAS来实现，保证了自增操作的原子性
     */
    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    //num++;
                    atomicInteger.incrementAndGet();
                }
            }).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 如果不保证原子性的情况下，可能会出现num值和预期不一致
        //System.out.println(num);
        // 如果使用保证原子性的变量，可以达到预期
        System.out.println(atomicInteger);
    }
}
