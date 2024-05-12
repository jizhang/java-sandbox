package com.shzhangji.javasandbox.concurrency;

import java.util.concurrent.RecursiveTask;

public class ForkJoinFibonacci {
  // https://stackoverflow.com/a/37257685/1030720
  static class FibonacciTask extends RecursiveTask<Long> {
    final int n;

    FibonacciTask(int n) {
      this.n = n;
    }

    @Override
    protected Long compute() {
      return fib(n);
    }

    long fib(int n) {
      assert n >= 0;
      if (n <= 1) return n;

      if (n > 10 && getSurplusQueuedTaskCount() < 2) {
        var f1 = new FibonacciTask(n - 1);
        var f2 = new FibonacciTask(n - 2);
        f1.fork();
        return f2.compute() + f1.join();
      } else {
        return fib(n - 1) + fib(n - 2);
      }
    }
  }

  static long topDown(int n) {
    assert n >= 0;
    if (n <= 1) return n;
    return topDown(n - 1) + topDown(n - 2);
  }

  static long bottomUp(int n) {
    assert n >= 0;
    if (n <= 1) return n;

    var dp = new long[n + 1];
    dp[1] = 1;
    for (int i = 2; i <= n; ++i) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
  }

  public static void main(String[] args) throws Exception {
    final int n = 50;
    long start = System.currentTimeMillis();
    long result = topDown(n);
    System.out.printf("Top-down takes %.2f seconds, result=%d%n", (System.currentTimeMillis() - start) / 1000.0, result);

    start = System.currentTimeMillis();
    result = bottomUp(n);
    System.out.printf("Bottom-up takes %.2f seconds, result=%d%n", (System.currentTimeMillis() - start) / 1000.0, result);

    start = System.currentTimeMillis();
    var f = new FibonacciTask(n);
    result = f.fork().join();
    System.out.printf("Fork-join takes %.2f seconds, result=%d%n", (System.currentTimeMillis() - start) / 1000.0, result);
  }
}
