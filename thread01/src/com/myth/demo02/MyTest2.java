package com.myth.demo02;

/**
 * synchronized
 */
public class MyTest2 {
    public static void main(String[] args) {
        MyTickett tickett = new MyTickett();
        Thread thread01 = new Thread(tickett, "一号窗口");
        Thread thread02 = new Thread(tickett, "二号窗口");

        thread01.start();
        thread02.start();
    }
}


class MyTickett implements Runnable{

    /**
     * 加入static表示当前变量输入当前类所共享的
     */
    static int num = 1;

    @Override
    public void run() {

        while (num<=100){
            // demo01();
            demo02();
        }
    }

    /**
     * synchronized修饰成员方法
     * 此时锁的参数就是this
     * 应为当前锁是对象锁（this）所以必须保证使用的对象是同一个对象
     */
    public synchronized void demo01(){
        if (num<=100){
            System.out.println(Thread.currentThread().getName()+"，卖出了第："+num+"张票");
            num++;
        }else {
            System.out.println("票已经买完了！");
        }
    }
    /**
     * synchronized修饰静态成员方法
     * 此时锁的参数就是类锁，传递对象的字节码 .class
     * 同一个类创建出不同对象，他们对应的.getClass()是一样的
     */
    public static synchronized void demo02(){
        if (num<=100){
            System.out.println(Thread.currentThread().getName()+"，卖出了第："+num+"张票");
            num++;
        }else {
            System.out.println("票已经买完了！");
        }
    }

}