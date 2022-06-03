package com.myth.demo02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 新建读写锁
 */
public class TestA {
    public static void main(String[] args) {

        // 获取读和写的锁
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        // 获取读锁
        ReentrantReadWriteLock.ReadLock readLock1 = reentrantReadWriteLock.readLock();
        // 获取写锁
        ReentrantReadWriteLock.WriteLock writeLock1 = reentrantReadWriteLock.writeLock();

        // 多态   父类引用指向子类对象
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock writeLock2 = readWriteLock.writeLock();
        Lock writeLock3 = readWriteLock.writeLock();
        Lock readLock2 = readWriteLock.readLock();
        Lock readLock3 = readWriteLock.readLock();

        // 获取读写锁时，获得是同一把锁（读和读是一把锁，写和写是一把锁，读和写是两把锁）
        System.out.println(writeLock2 == writeLock3);
        System.out.println(readLock2 == readLock3);
        System.out.println(writeLock2 == readLock2);

    }
}
