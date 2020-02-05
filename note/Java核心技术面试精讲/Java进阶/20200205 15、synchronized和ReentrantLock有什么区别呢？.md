## synchronized和ReentrantLock有什么区别呢？

开场问题： synchronized 和 ReentrantLock 有什么区别？有人说 synchronized 最慢，这话靠谱吗？

### 典型回答
synchronized是Java内建的同步机制。所以也有人称其为Intrinsic Locking，他提供了互斥的语义和可见性，当一个线程已经获取当前锁时，
其他视图获取的线程只能等待或者阻塞在那里。

在Java5之前，synchronized是唯一的同步手段。

Reentrantlock，通常翻译为再入锁，Java5提供。他的语义和synchronized基本相同。再入锁通过代码直接调取lock()方法获取。代码书写也更为灵活。

与此同时，ReentrantLock提供了很多实用的方法，可以实现很多synchronized无法做到的细节控制。但是编码之中也需要注意，明确强调调用unlock()方法释放，否则会一直持有该锁。

synchronized和Reentrantlock的性能不能一概而论，目前在低竞争场景中synchronized可能表现更优秀。

### 考点分析
以下知识点需要掌握：
- 理解什么是线程安全
- synchronize，Reetrantlock等机制的基本使用与案例
- 掌握synchronized、ReetrantLock的底层实现；理解锁膨胀、降级；理解偏斜锁、自旋锁、轻量级锁、重量级锁等概念
- 掌握并发包中java.util.concurrent.lock各种不同的实现和案例分析。

### 知识扩展
#### 线程安全
定义：线程安全是一个多线程环境下正确性的概念，也就是保证多线程环境下**共享的、可修改的**状态的正确性。这里的状态反映在程序中其实可以看做是数据。
- 封装：通过封装，我们可以将对象内部隐藏，保护起来。
- 不可变

线程安全需要保证几个基本特性：
- 原子性：相关操作不会被其他操作打扰。一般通过同步机制实现
- 可见性：一个线程修改了某个共享变量，其状态立即被其他线程知晓（将线程本地状态反映到主内村上，volatile就是负责保证可见性）
- 有序性 ：保证线程内串行语义，避免指令重排。

以下为示例代码：
```
public class ThreadSafeSample {
  public int sharedState;
  public void nonSafeAction() {
      while (sharedState < 100000) {
          int former = sharedState++;
          int latter = sharedState;
          if (former != latter - 1) {
              System.out.printf("Observed data race, former is " +
                      former + ", " + "latter is " + latter);
          }
      }
  }
  //两个线程的低度并发，遇到former和latter不相等的情况。
  //这是因为，在两次取值中，其他线程已经修改了sharedState
  public static void main(String[] args) throws InterruptedException {
      ThreadSafeSample sample = new ThreadSafeSample();
      Thread threadA = new Thread(){
          public void run(){
              sample.nonSafeAction();
          }
      };
      Thread threadB = new Thread(){
          public void run(){
              sample.nonSafeAction();
          }
      };
      threadA.start();
      threadB.start();
      threadA.join();
      threadB.join();
  }
}
```
最后的运行结果：
```
C:\>c:\jdk-9\bin\java ThreadSafeSample
Observed data race, former is 13097, latter is 13099
```
将两次赋值用synchronized保护起来，使用this作为互斥单元，就额可以比米娜别的线程并发的去修改sharedState
```
synchronized (this) {
  int former = sharedState ++;
  int latter = sharedState;
  // …
}
```
代码中使用 synchronized 非常便利，如果用来修饰静态方法，其等同于利用下面代码将方法体囊括进来：
```synchronized (ClassName.class) {}
```
这里在看ReentrantLock。如何理解再入？
当一个线程试图获取它已经获取的锁的时候，这个获取就自动成功了。这是对锁获取粒度的一个概念，也就是锁的持有是一线成为单位而不是
基于调用次数。java锁实现强调再入性是为了和pthread的行为进行区分。

再入锁可以设置公平性（fairness），我们可在创建再入锁时选择是否是公平的。
```
ReentrantLock fairLock = new ReentrantLock(true);
```
公平性：公平性为真时，会倾向于将锁赋予等待时间更久的线程。公平性是减少线程“饥饿”的情况发生（个别线程长时间等待锁但始终无法获取）。

但使用synchronized，我们无法进行公平性原则。其实永远是不公平的。这也就是主流操作系统线程调用的选择。通用场景中，公平性未必有想象中的那么重要。
java磨人的调度策略很少会导致“饥饿”的发生。

与此同时，为保证公平性引入额外开销，肯定会导致一定的吞吐量下降。所以这里注意不要过度设计。

从日常编码的角度学习再入锁。为保证锁释放，没一个lock()动作都报过一个try-catch-finally 
```
ReentrantLock fairLock = new ReentrantLock(true);// 这里是演示创建公平锁，一般情况不需要。
fairLock.lock();
try {
  // do something
} finally {
   fairLock.unlock();
}
```
ReentrantLock相比synchronized，因为可以像普通对象一样使用，所以可以利用其提供的各种便利方法进行惊喜的同步操作。
- 带超时的获取锁尝试
- 可以判断是否有线程，或者某个特定线程，在排队等待获取锁
- 可以响应中断请求

条件变量：如果说ReentrantLock是synchronized的替代选择，Condition是将wait、notify、notifyAll等操作转化为相应的对象，将复杂而晦涩的同步从左转变成为直接可控的对象行为。

应用场景ArrayBlockingQueue：
1. 通过再入锁获取条件变量
```
/** Condition for waiting takes */
private final Condition notEmpty;

/** Condition for waiting puts */
private final Condition notFull;
 
public ArrayBlockingQueue(int capacity, boolean fair) {
  if (capacity <= 0)
      throw new IllegalArgumentException();
  this.items = new Object[capacity];
  lock = new ReentrantLock(fair);
  notEmpty = lock.newCondition();
  notFull =  lock.newCondition();
}
```
2. 两个条件变量是从同一再入锁创建出来，然后使用在特定操作中，如下面的 take 方法，判断和等待条件满足：
```$xslt
public E take() throws InterruptedException {
  final ReentrantLock lock = this.lock;
  lock.lockInterruptibly();
  try {
      while (count == 0)
          notEmpty.await();
      return dequeue();
  } finally {
      lock.unlock();
  }
}
```
3. 当队列为空时，试图 take 的线程的正确行为应该是等待入队发生，而不是直接返回，这是 BlockingQueue 的语义，使用条件 notEmpty 就可以优雅地实现这一逻辑。那么，怎么保证入队触发后续 take 操作呢？请看 enqueue 实现：
```$xslt
private void enqueue(E e) {
  // assert lock.isHeldByCurrentThread();
  // assert lock.getHoldCount() == 1;
  // assert items[putIndex] == null;
  final Object[] items = this.items;
  items[putIndex] = e;
  if (++putIndex == items.length) putIndex = 0;
  count++;
  notEmpty.signal(); // 通知等待的线程，非空条件已经满足
}
```
4. 通过 signal/await 的组合，完成了条件判断和通知等待线程，非常顺畅就完成了状态流转。注意，signal 和 await 成对调用非常重要，不然假设只有 await 动作，线程会一直等待直到被打断（interrupt）。

高竞争情况下，ReentrantLock 仍然有一定优势。我在下一讲进行详细分析，会更有助于理解性能差异产生的内在原因。在大多数情况下，无需纠结于性能，还是考虑代码书写结构的便利性、可维护性等。