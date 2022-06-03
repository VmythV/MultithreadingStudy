package com.myth.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock锁是可重入锁
 */
public class TestB {
    public static void main(String[] args) {
        new Thread(new LockRunnable(),"AAA").start();
        new Thread(new LockRunnable(),"BBB").start();
    }
}

class LockRunnable implements Runnable{

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        // 第一层锁
        lock.lock();
        try {
            System.out.println("第一层");
            // 第二层锁
            lock.lock();
            try {
                System.out.println("第二层");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}