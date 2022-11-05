package com.shzhangji.algorithm.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

// https://leetcode.com/problems/print-zero-even-odd/
public class ZeroEvenOdd {
  public static void main(String[] args) {
    var obj = new ZeroEvenOdd(5);
    var executor = Executors.newCachedThreadPool();
    executor.submit(() -> {
      try {
        obj.zero(System.out::print);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    executor.submit(() -> {
      try {
        obj.odd(System.out::print);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    executor.submit(() -> {
      try {
        obj.even(System.out::print);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    executor.shutdown();
  }

  private int n;
  Semaphore s0, s1, s2;

  public ZeroEvenOdd(int n) {
    this.n = n;
    s0 = new Semaphore(1);
    s1 = new Semaphore(0);
    s2 = new Semaphore(0);
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void zero(IntConsumer printNumber) throws InterruptedException {
    for (int i = 1; i <= n; ++i) {
      s0.acquire();
      printNumber.accept(0);
      if (i % 2 > 0) {
        s1.release();
      } else {
        s2.release();
      }
    }
  }

  public void odd(IntConsumer printNumber) throws InterruptedException {
    for (int i = 1; i <= n; i += 2) {
      s1.acquire();
      printNumber.accept(i);
      s0.release();
    }
  }

  public void even(IntConsumer printNumber) throws InterruptedException {
    for (int i = 2; i <= n; i += 2) {
      s2.acquire();
      printNumber.accept(i);
      s0.release();
    }
  }
}
