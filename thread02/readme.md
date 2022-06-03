# Lock锁
### lock锁
lock()方法是使用的最多的一个方法，就是用来获取锁。如果锁已被其他线程获取，则进行等待

如果采用Lock，必须主动去释放锁，并且在发生异常时，不会自动释放锁，因此，使用Lock必须在try-catch块中进行，并且将释放锁的操作放入finally中进行，以保证锁一定被释放，防止死锁的发生，

#### tryLock()
tryLock()方法是有返回值的，它表示用来尝试获取锁，如果获取成功，则返回true，如果获取失败(即锁已被其他线程获取)，则返回false，也就是说这个方法无论如何都会立即返回，拿不到锁时不会一直在那等待。

#### try(long time, TimeUnit unit)
try(long time, TimeUnit unit)方法和tryLock()方法是类似的，只不过区别在于这个方法在拿不到锁时会等待一定时间，在时间期限之内如果还拿不到锁，就返回false。如果一开始拿到锁或者在等待期间内拿到了锁，则返回true

#### lockInterruptibly()
lockInterruptibly()方法比较特殊，当通过这个方法去获取锁时，如果线程正在等待获取锁，则这个线程能够响应中断，可以通过.interrupt()方法中断线程的等待过程。

### ReadWriteLock锁
读写锁原则：
- 读和读之间是一个共享
- 写和写之间是一个互斥
- 读和写之间是一个互斥
```java
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

```

# Lock锁和同步锁（synchronized）的选择
### 1.类型不同
synchronized是关键字。修饰方法，修饰代码块

Lock是接口

### 2.加锁和解锁机制同步
synchronized是自动加锁和解锁，程序员不需要控制

Lock必须是程序员控制加锁和解锁过程，解锁时，需要注意出现异常不会自动解锁

### 3.异常机制
synchronized碰到没有处理的异常，会自动解锁，不会出现死锁。

Lock碰到异常不会自动解锁，可能出现死锁。所以写Lock锁时都是要把解锁放入finally{}中。

### 4.Lock功能更强大
Lock里面提供了try()/isLocked()方法，进行判断是否上锁成功。

synchronized因为是关键字，所以无法判断。

### 5.Lock性能更优
如果多线程竞争锁比较激烈时，Lock的性能更优。如果竞争不激烈，性能相差不大。

### 6.锁住内容不同
synchronized可以锁方法

Lock只可以锁代码块