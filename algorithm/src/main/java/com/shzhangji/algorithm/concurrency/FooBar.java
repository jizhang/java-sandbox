package com.shzhangji.algorithm.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

// https://leetcode.com/problems/print-foobar-alternately/
public class FooBar {
  public static void main(String[] args) {
    var obj = new FooBar(3);
    var executor = Executors.newCachedThreadPool();
    executor.submit(() -> {
      try {
        obj.foo(() -> { System.out.print("foo"); });
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    executor.submit(() -> {
      try {
        obj.bar(() -> { System.out.print("bar"); });
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    executor.shutdown();
  }

  private int n;
  Semaphore s1, s2;

  public FooBar(int n) {
    this.n = n;
    s1 = new Semaphore(1);
    s2 = new Semaphore(0);
  }

  public void foo(Runnable printFoo) throws InterruptedException {
    for (int i = 0; i < n; i++) {
      s1.acquire();
      // printFoo.run() outputs "foo". Do not change or remove this line.
      printFoo.run();
      s2.release();
    }
  }

  public void bar(Runnable printBar) throws InterruptedException {
    for (int i = 0; i < n; i++) {
      s2.acquire();
      // printBar.run() outputs "bar". Do not change or remove this line.
      printBar.run();
      s1.release();
    }
  }
}
