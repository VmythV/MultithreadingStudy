package com.myth.demo01;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock解除死锁状态
 */
public class TestC {
    public static void main(String[] args) {
        ReentrantLock lockA = new ReentrantLock();
        ReentrantLock lockB = new ReentrantLock();

        new Thread(new Person1(lockA,lockB)).start();
        new Thread(new Person2(lockA,lockB)).start();
    }
}

class Person1 implements Runnable{

    ReentrantLock resourceA;
    ReentrantLock resourceB;

    public Person1(ReentrantLock resourceA, ReentrantLock resourceB) {
        this.resourceA = resourceA;
        this.resourceB = resourceB;
    }

    @Override
    public void run() {
        resourceA.lock();
        try {
            System.out.println("Person1获得了ResourceA");
            // 如果B被其他线程获得了，则放弃A
            if (resourceB.isLocked()){
                resourceA.unlock();
                System.out.println("Person1放弃了resourceA");
            }
            resourceB.lock();
            try {
                System.out.println("Person1同时获得了ResourceA和ResourceB");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                resourceB.unlock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 如果A被锁住了，则解锁A
            if (resourceA.isLocked()){
                resourceA.unlock();
            }

        }

    }
}

class Person2 implements Runnable{

    ReentrantLock resourceA;
    ReentrantLock resourceB;

    public Person2(ReentrantLock resourceA, ReentrantLock resourceB) {
        this.resourceA = resourceA;
        this.resourceB = resourceB;
    }

    @Override
    public void run() {
        resourceB.lock();
        try {
            System.out.println("Person2获得了ResourceB");
            if (resourceA.isLocked()){
                resourceB.unlock();
                System.out.println("Person2放弃了resourceB");
            }
            resourceA.lock();
            try {
                System.out.println("Person2同时获得了ResourceA和ResourceB");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                resourceA.unlock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resourceB.isLocked()){
                resourceB.unlock();
            }
        }

    }
}