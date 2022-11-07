package com.shzhangji.algorithm.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

// https://leetcode.com/problems/fizz-buzz-multithreaded/
public class FizzBuzz {
  public static void main(String[] args) {
    var obj = new FizzBuzz(15);

    var executor = Executors.newCachedThreadPool();

    executor.submit(() -> {
      try {
        obj.fizz(() -> System.out.println("fizz"));
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
    executor.submit(() -> {
      try {
        obj.buzz(() -> System.out.println("buzz"));
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
    executor.submit(() -> {
      try {
        obj.fizzbuzz(() -> System.out.println("fizzbuzz"));
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
    executor.submit(() -> {
      try {
        obj.number(System.out::println);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    executor.shutdown();
  }

  private int n;
  int current;
  ReentrantLock lock;
  Condition condition;

  public FizzBuzz(int n) {
    this.n = n;
    current = 1;
    lock = new ReentrantLock();
    condition = lock.newCondition();
  }

  // printFizz.run() outputs "fizz".
  public void fizz(Runnable printFizz) throws InterruptedException {
    while (current <= n) {
      lock.lock();
      try {
        if (current % 3 == 0 && current % 5 != 0) {
          printFizz.run();
          ++current;
          condition.signalAll();
        } else {
          condition.await();
        }
      } finally {
        lock.unlock();
      }
    }
  }

  // printBuzz.run() outputs "buzz".
  public void buzz(Runnable printBuzz) throws InterruptedException {
    while (current <= n) {
      lock.lock();
      try {
        if (current % 5 == 0 && current % 3 != 0) {
          printBuzz.run();
          ++current;
          condition.signalAll();
        } else {
          condition.await();
        }
      } finally {
        lock.unlock();
      }
    }
  }

  // printFizzBuzz.run() outputs "fizzbuzz".
  public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
    while (current <= n) {
      lock.lock();
      try {
        if (current % 3 == 0 && current % 5 == 0) {
          printFizzBuzz.run();
          ++current;
          condition.signalAll();
        } else {
          condition.await();
        }
      } finally {
        lock.unlock();
      }
    }
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void number(IntConsumer printNumber) throws InterruptedException {
    while (current <= n) {
      lock.lock();
      try {
        if (current % 3 != 0 && current % 5 != 0) {
          printNumber.accept(current);
          ++current;
          condition.signalAll();
        } else {
          condition.await();
        }
      } finally {
        lock.unlock();
      }
    }
  }
}
