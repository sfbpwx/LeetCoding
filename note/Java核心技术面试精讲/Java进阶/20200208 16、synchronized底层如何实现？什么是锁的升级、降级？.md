## synchronized底层如何实现？什么是锁的升级、降级？

最新的JDK中，JVM提供了三种不同的monitor实现，也就是三种不同的锁：偏斜锁、轻量级锁和重量级锁。大大改进了其性能。

所谓锁的升级、降级，就是JVM优化synchronized的机制。当JVM监测到不同的竞争状况时，会自动切换到合适的锁实现，这种切换就是锁的升级、降级。

当没有竞争出现的时候，默认会使用偏斜锁。JVM会使用CAS操作（compare and swap），在对象头上的MarkWord部分设置线程ID，以表示这个对象偏向于
当前线程。所以不涉及真正的互斥锁。因为大部分对象生命周期最多被一个线程锁定，使用偏斜锁可以降低无竞争的开销。

如果有另外的线程试图锁定某个被偏斜过的的对象，JVM就需要撤销偏斜锁，并更换为轻量级锁。

锁降级会发生在，当 JVM进入安全点的时候，会检查是否有闲置的monitor，然后试图进行降级。

### 考点分析
- 从源码层面，稍微展开一些 synchronized 的底层实现，并补充一些上面答案中欠缺的细节，有同学反馈这部分容易被问到。如果你对 Java 底层源码有兴趣，但还没有找到入手点，这里可以成为一个切入点。
- 理解并发包中 java.util.concurrent.lock 提供的其他锁实现，毕竟 Java 可不是只有 ReentrantLock 一种显式的锁类型，我会结合代码分析其使用。

### 知识扩展
synchronized 是 JVM 内部的 Intrinsic Lock，所以偏斜锁、轻量级锁、重量级锁的代码实现，并不在核心类库部分，而是在 JVM 的代码中。
```
Handle h_obj(THREAD, obj);
  if (UseBiasedLocking) {
    // Retry fast entry if bias is revoked to avoid unnecessary inflation
    ObjectSynchronizer::fast_enter(h_obj, lock, true, CHECK);
  } else {
    ObjectSynchronizer::slow_enter(h_obj, lock, CHECK);
  }
```
