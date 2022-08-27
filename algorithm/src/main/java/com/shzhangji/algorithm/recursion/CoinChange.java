package com.shzhangji.algorithm.recursion;

// https://leetcode.com/problems/coin-change/
// https://leetcode.com/problems/coin-change/solution/
public class CoinChange {
  public static void main(String[] args) {
    var obj = new com.shzhangji.algorithm.dp.CoinChange();
    System.out.println(obj.coinChange(new int[] { 186, 419, 83, 408 }, 6249)); // 20
  }

  public int coinChange(int[] coins, int amount) {
    return coinChange(coins, amount, new int[amount + 1]);
  }

  int coinChange(int[] coins, int amount, int[] mem) {
    if (amount == 0) {
      return 0;
    }

    if (mem[amount] != 0) {
      return mem[amount];
    }

    int minCount = Integer.MAX_VALUE;
    for (int coin : coins) {
      if (coin <= amount) {
        int count = coinChange(coins, amount - coin, mem);
        if (count >= 0 && count < minCount) {
          minCount = count;
        }
      }
    }

    if (minCount != Integer.MAX_VALUE) {
      mem[amount] = minCount + 1;
    } else {
      mem[amount] = -1;
    }

    return mem[amount];
  }
}
