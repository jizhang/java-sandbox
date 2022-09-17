package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/coin-change/
// https://leetcode.com/problems/coin-change/solution/
// https://leetcode.com/problems/coin-change/discuss/1660474/Recursion-DP-using-2D-array-and-DP-using-1D-array-Java-Solutions
// https://www.youtube.com/watch?v=J2eoCvk59Rc
public class CoinChange {
  public static void main(String[] args) {
    var obj = new CoinChange();
    System.out.println(obj.coinChange(new int[] { 1, 2, 5 }, 11)); // 3
    System.out.println(obj.coinChange(new int[] { 2 }, 3)); // -1
    System.out.println(obj.coinChange(new int[] { 1 }, 0)); // 0
    System.out.println(obj.coinChange(new int[] { 186, 419, 83, 408 }, 6249)); // 20
    System.out.println(obj.coinChange(new int[] { 2, 3, 5, 10 }, 15)); // 2
  }

  public int coinChange(int[] coins, int amount) {
    return bottomUp(coins, amount);
  }

  int bottomUp(int[] coins, int amount) {
    final int max = amount + 1;
    var dp = new int[coins.length + 1][amount + 1];
    for (int j = 1; j <= amount; ++j) {
      dp[0][j] = max;
    }

    for (int i = 1; i <= coins.length; ++i) {
      for (int j = 1; j <= amount; ++j) {
        if (coins[i - 1] <= j) {
          dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    int result = dp[coins.length][amount];
    return result == max ? -1 : result;
  }

  int bottomUp1D(int[] coins, int amount) {
    final int max = amount + 1;
    var dp = new int[amount + 1];

    for (int j = 1; j <= amount; ++j) {
      dp[j] = max;
      for (int coin : coins) {
        if (coin <= j) {
          dp[j] = Math.min(dp[j], dp[j - coin] + 1);
        }
      }
    }

    return dp[amount] == max ? -1 : dp[amount];
  }

  int topDown(int[] coins, int amount) {
    return topDown(coins, amount, new int[amount + 1]);
  }

  int topDown(int[] coins, int amount, int[] memo) {
    if (amount == 0) return 0;
    if (memo[amount] != 0) return memo[amount];

    int min = Integer.MAX_VALUE;
    for (int coin : coins) {
      if (coin <= amount) {
        int result = 1 + topDown(coins, amount - coin, memo);
        if (result > 0 && result < min) {
          min = result;
        }
      }
    }

    memo[amount] = min == Integer.MAX_VALUE ? -1 : min;
    return memo[amount];
  }
}
