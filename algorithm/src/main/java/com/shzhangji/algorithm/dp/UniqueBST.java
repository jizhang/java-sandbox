package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/unique-binary-search-trees/
public class UniqueBST {
  public static void main(String[] args) {
    var obj = new UniqueBST();
    System.out.println(obj.numTrees(3)); // 5
    System.out.println(obj.numTrees(1)); // 1
    System.out.println(obj.numTrees(4)); // 14
  }

  public int numTrees(int n) {
    return bottomUp(n);
  }

  int bottomUp(int n) {
    var dp = new int[n + 1];
    dp[1] = 1;
    for (int i = 2; i <= n; ++i) {
      for (int j = 1; j <= i; ++j) {
        if (j == 1 || j == i) dp[i] += dp[i - 1];
        else dp[i] += dp[j - 1] * dp[i - j];
      }
    }
    return dp[n];
  }

  int topDown(int n) {
    return topDown(n, new int[n + 1]);
  }

  int topDown(int n, int[] memo) {
    if (n == 1) return 1;
    if (memo[n] > 0) return memo[n];
    int result = 0;
    for (int i = 1; i <= n; ++i) {
      if (i == 1 || i == n) result += topDown(n - 1, memo);
      else result += topDown(i - 1, memo) * topDown(n - i, memo);
    }
    memo[n] = result;
    return result;
  }
}
