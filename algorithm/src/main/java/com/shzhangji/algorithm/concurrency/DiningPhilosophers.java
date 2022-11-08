package com.shzhangji.algorithm.concurrency;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

// https://leetcode.com/problems/the-dining-philosophers/
public class DiningPhilosophers {
  public static void main(String[] args) {
    var obj = new DiningPhilosophers();
    var executor = Executors.newCachedThreadPool();

    for (int i = 0; i < 1; ++i) {
      for (int j = 0; j < 5; ++j) {
        int n = j;
        executor.submit(() -> {
          try {
            obj.wantsToEat(n, make(n, 1, 1), make(n, 2, 1), make(n, 0, 3), make(n, 1, 2), make(n, 2, 2));
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
      }
    }

    executor.shutdown();
  }

  static Runnable make(int a, int b, int c) {
    return () -> System.out.printf("[%d,%d,%d]%n", a, b, c);
  }

  Semaphore s;
  Object[] forks;

  public DiningPhilosophers() {
    s = new Semaphore(4); // Prevent deadlock.
    forks = new Object[5];
    Arrays.fill(forks, new Object());
  }

  // call the run() method of any runnable to execute its code
  public void wantsToEat(int philosopher,
                         Runnable pickLeftFork,
                         Runnable pickRightFork,
                         Runnable eat,
                         Runnable putLeftFork,
                         Runnable putRightFork) throws InterruptedException {

    s.acquire();
    try {
      synchronized (forks[philosopher]) {
        pickLeftFork.run();
        synchronized (forks[(philosopher + 1) % 5]) {
          pickRightFork.run();
          eat.run();
          putRightFork.run();
        }
        putLeftFork.run();
      }
    } finally {
      s.release();
    }
  }
}
