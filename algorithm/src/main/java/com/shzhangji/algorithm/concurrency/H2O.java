package com.shzhangji.algorithm.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// https://leetcode.com/problems/building-h2o/
// https://leetcode.com/problems/building-h2o/discuss/576477/Java-conditions-and-lock-simple-beats-100-explained
public class H2O {
  public static void main(String[] args) {
    var obj = new H2O();

    "HHHOOOHHH".chars().parallel().forEach(c -> {
      try {
        if (c == 'H') obj.hydrogen(() -> System.out.print("H"));
        else obj.oxygen(() -> System.out.print("O"));
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
  }

  ReentrantLock lock;
  Condition releaseH, releaseO;
  int countH = 0;

  public H2O() {
    lock = new ReentrantLock();
    releaseH = lock.newCondition();
    releaseO = lock.newCondition();
  }

  public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
    lock.lock();
    try {
      while (countH == 2) releaseO.await();
      // releaseHydrogen.run() outputs "H". Do not change or remove this line.
      releaseHydrogen.run();
      ++countH;
      releaseH.signal();
    } finally {
      lock.unlock();
    }
  }

  public void oxygen(Runnable releaseOxygen) throws InterruptedException {
    lock.lock();
    try {
      while (countH < 2) releaseH.await();
      // releaseOxygen.run() outputs "O". Do not change or remove this line.
      releaseOxygen.run();
      countH = 0;
      releaseO.signal();
    } finally {
      lock.unlock();
    }
  }
}
