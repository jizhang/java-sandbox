package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/coin-change-ii/
public class CoinChange2 {
  public static void main(String[] args) {
    var obj = new CoinChange2();
    System.out.println(obj.change(5, new int[] { 1, 2, 5 })); // 4
    System.out.println(obj.change(3, new int[] { 2 })); // 0
    System.out.println(obj.change(10, new int[] { 10 })); // 1
    System.out.println(obj.change(10, new int[] { 5 })); // 1
  }

  public int change(int amount, int[] coins) {
    return topDown(amount, coins);
  }

  int topDown(int amount, int[] coins) {
    var memo = new Integer[coins.length][amount + 1];
    return topDown(amount, coins, coins.length - 1, memo);
  }

  int topDown(int amount, int[] coins, int i, Integer[][] memo) {
    // if (i == 0) return amount % coins[i] == 0 ? 1 : 0;
    if (amount == 0) return 1;
    if (i == -1) return 0;
    if (memo[i][amount] != null) return memo[i][amount];

    int result = topDown(amount, coins, i - 1, memo);
    if (coins[i] <= amount) {
      result += topDown(amount - coins[i], coins, i, memo);
    }

    memo[i][amount] = result;
    return result;
  }

  int bottomUp(int amount, int[] coins) {
    var dp = new int[coins.length + 1][amount + 1];
    dp[0][0] = 1;

    for (int i = 1; i <= coins.length; ++i) {
      for (int j = 0; j <= amount; ++j) {
        dp[i][j] = dp[i - 1][j];
        if (coins[i - 1] <= j) {
          dp[i][j] += dp[i][j - coins[i - 1]];
        }
      }
    }

    return dp[coins.length][amount];
  }

  int bottomUp1D(int amount, int[] coins) {
    var dp = new int[amount + 1];
    dp[0] = 1;

    for (int coin : coins) {
      for (int j = coin; j <= amount; ++j) {
        dp[j] += dp[j - coin];
      }
    }

    return dp[amount];
  }
}
