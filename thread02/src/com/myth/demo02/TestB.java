package com.myth.demo02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁一般原则：
 *      读和读之间是一个共享
 *      写和写之间是一个互斥
 *      读和写之间是一个互斥
 */
public class TestB {
    public static void main(String[] args) {
        //operator1();
        //operator2();
        operator3();

    }

    /**
     * 不加锁的情况：在写的时候，不互斥，导致两个线程同时写
     */
    public static void operator1(){
        new Thread(() -> {
            new Operator1().read();
        }, "A").start();

        new Thread(() -> {
            new Operator1().read();
        }, "B").start();

        new Thread(() -> {
            new Operator1().write();
        }, "C").start();

        new Thread(() -> {
            new Operator1().write();
        }, "D").start();
    }

    /**
     * 加正常锁的情况：可以保证写操作互斥，但无法保证多个读线程同时读
     */
    public static void operator2(){
        new Thread(() -> {
            new Operator2().read();
        }, "A").start();

        new Thread(() -> {
            new Operator2().read();
        }, "B").start();

        new Thread(() -> {
            new Operator2().write();
        }, "C").start();

        new Thread(() -> {
            new Operator2().write();
        }, "D").start();
    }

    /**
     * 加读写锁的情况：可以满足读写原则
     */
    public static void operator3(){
        new Thread(() -> {
            new Operator3().read();
        }, "A").start();

        new Thread(() -> {
            new Operator3().read();
        }, "B").start();

        new Thread(() -> {
            new Operator3().write();
        }, "C").start();

        new Thread(() -> {
            new Operator3().write();
        }, "D").start();
    }
}

/**
 * 不加锁情况
 */
class Operator1 {
    /**
     * 写方法
     */
    public void write() {
        System.out.println(Thread.currentThread().getName() + "开始写内容");

        // 模拟写的时候需要一定时间
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "结束写内容");
    }

    /**
     * 读方法
     */
    public void read() {
        // 模拟开始读内容
        System.out.println(Thread.currentThread().getName() + "开始读内容");
        // 模拟读的时候需要一定时间
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 模拟结束读内容
        System.out.println(Thread.currentThread().getName() + "结束读内容");
    }
}

/**
 * 加正常锁情况
 */
class Operator2 {

    static Lock lock = new ReentrantLock();

    /**
     * 写方法
     */
    public void write() {
        lock.lock();
        try {
            // 模拟开始写内容
            System.out.println(Thread.currentThread().getName() + "开始写内容");
            // 模拟写的时候需要一定时间
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 模拟结束写内容
            System.out.println(Thread.currentThread().getName() + "结束写内容");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 读方法
     */
    public void read() {
        lock.lock();
        try {
            // 模拟开始读内容
            System.out.println(Thread.currentThread().getName() + "开始读内容");
            // 模拟读的时候需要一定时间
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 模拟结束读内容
            System.out.println(Thread.currentThread().getName() + "结束读内容");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 加正常锁情况
 */
class Operator3 {

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 写方法
     */
    public void write() {
        readWriteLock.writeLock().lock();
        try {
            // 模拟开始写内容
            System.out.println(Thread.currentThread().getName() + "开始写内容");
            // 模拟写的时候需要一定时间
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 模拟结束写内容
            System.out.println(Thread.currentThread().getName() + "结束写内容");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * 读方法
     */
    public void read() {
        readWriteLock.readLock().lock();
        try {
            // 模拟开始读内容
            System.out.println(Thread.currentThread().getName() + "开始读内容");
            // 模拟读的时候需要一定时间
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 模拟结束读内容
            System.out.println(Thread.currentThread().getName() + "结束读内容");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

