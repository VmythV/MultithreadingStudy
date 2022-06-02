### 实现线程的第一种方式：继承Thread类

1.继承Thread类实现线程   重写run()方法

2.使用start()开启子线程

3.调用start()——>底层调用start0()  底层使用native方法，不是java写的 ——> run()
### 实现线程的第二种方式：实现Runnable接口

1.实现Runnable接口，重写run()方法

2.直接调用start()不可以开启，需要使用Thread有参构造进行开启线程

3.Thread——>implements Runnable
### 实现线程的第三种方式：实现Callable接口

最大特点：可以接收线程执行方法结束后的返回值

### 总结
1.后两种方法可以避免java中的单继承的局限性

2.需要返回值只能实现Callable接口

3.不需要返回值推荐使用实现Runnable接口

4.线程池只能使用Runnable或Callable类型参数，不能使用继承Thread类

### 如何实现线程同步
#### synchronized同步锁
同步代码块锁
```java
synchronized (obj){}
```
同步方法锁
```java
private synchronized void test(int amt){}
```

#### volatile+CAS无锁化方案

#### Lock锁
ReentrantLock、ReentrantReadWriteLock

### JMM总结
1.volatile可以解决可见性、有序性，但是不能解决原子性
2.synchronize/Lock可以解决可见性，有序性，原子性