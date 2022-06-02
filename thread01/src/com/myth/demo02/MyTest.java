package com.myth.demo02;

/**
 * synchronized
 */
public class MyTest {
    public static void main(String[] args) {
        Thread thread01 = new Thread(new MyTicket(), "一号窗口");
        Thread thread02 = new Thread(new MyTicket(), "二号窗口");
        /**
         * 使用this必须是同一个对象
         * MyTicket myTicket = new MyTicket();
         * Thread thread01 = new Thread(myTicket, "一号窗口");
         * Thread thread02 = new Thread(myTicket, "二号窗口");
         */

        thread01.start();
        thread02.start();
    }
}


class MyTicket implements Runnable{

    /**
     * 加入static表示当前变量输入当前类所共享的
     */
    static int num = 1;

    @Override
    public void run() {

        while (num<=100){
            /**
             * 加入锁，使得当前代码块仅有一个线程可以进入
             * 参数必须保证是同一把锁，不然锁不住代码块
             * A：固定值——“锁”
             * B:this 称为对象锁   注意：必须保证操作线程传递的对象是同一个
             * C:Object.class    存放的是一个Class类型 称为类锁
             */
            synchronized ("锁"){
                if (num<=100){
                    System.out.println(Thread.currentThread().getName()+"，卖出了第："+num+"张票");
                    num++;
                }else {
                    System.out.println("票已经买完了！");
                }
            }
        }
    }
}