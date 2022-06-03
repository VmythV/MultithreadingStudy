package com.myth.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 缺点：产生的死锁情况是没有办法处理，主要原因是加锁和释放锁的操作不是我们自己操作的，而是JVM完成的
 * Lock锁
 *      1.多个线程使用的时候必须是同一个锁对象
 *      2.使用Lock()进行代码加锁   unlock()进行代码解锁
 *      3.如果代码出现异常，程序不会自动释放锁，所以，使用lock锁时建议try-catch-finally
 */
public class TestA {

    public static void main(String[] args) {
        new Thread(new TicketRunnable(), "窗口1").start();
        new Thread(new TicketRunnable(), "窗口2").start();
    }

}

class TicketRunnable implements Runnable {

    static int num = 1;
    /**
     * 创建Lock锁
     */
    static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (num <= 100) {
            // 加锁 建议：在lock锁后紧跟try-catch-finally语句
            lock.lock();
            try {
                if (num <= 100) {
                    System.out.println(Thread.currentThread().getName() + "购买了第" + num + "张票");
                    num++;
                } else {
                    System.out.println("票已经买完了！");
                }
                // lock锁在锁之间如果发生错误，则会一直占有锁，不会释放，所以加上try-catch-finally能够在发生错误时，及时释放锁
                //int a = 10/0;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
            }
        }
    }
}