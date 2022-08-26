package com.shzhangji.algorithm.recursion;

// https://leetcode.com/problems/coin-change/
public class CoinChange {
  public static void main(String[] args) {
    var obj = new com.shzhangji.algorithm.dp.CoinChange();
    System.out.println(obj.coinChange(new int[] { 186, 419, 83, 408 }, 6249)); // 20
  }

  int[] mem;

  public int coinChange(int[] coins, int amount) {
    mem = new int[amount + 1];
    return coinChange0(coins, amount);
  }

  int coinChange0(int[] coins, int amount) {
    if (amount == 0) {
      return 0;
    }

    if (mem[amount] != 0) {
      return mem[amount];
    }

    int minCount = Integer.MAX_VALUE;
    for (int coin : coins) {
      if (coin <= amount) {
        int count = coinChange0(coins, amount - coin);
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
