package com.myth.demo02;

/**
 * 死锁案例
 * PersonA 和 PresonB 需要同时获取resourceA和resourceB才能正常工作
 * 死锁产生的四个条件
 * 1.互斥
 * 2.不可剥夺
 * 3.请求并保持
 * 4.循环等待
 */
public class MyTest3 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new PersonA());
        Thread thread2 = new Thread(new PersonB());
        thread1.start();
        thread2.start();
    }

}

class PersonA implements Runnable{

    @Override
    public void run() {
        synchronized ("resourceA"){
            System.out.println("PersonA拿到了resourceA");
            synchronized ("resourceB"){
                System.out.println("PersonA同时获取到A和B");
            }
        }
    }
}

class PersonB implements Runnable{

    @Override
    public void run() {
        synchronized ("resourceB"){
            System.out.println("PersonB拿到了resourceB");
            synchronized ("resourceA"){
                System.out.println("PersonB同时获取到A和B");
            }
        }
    }
}