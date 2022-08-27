package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/coin-change/
// https://leetcode.com/problems/coin-change/solution/
public class CoinChange {
  public static void main(String[] args) {
    var obj = new CoinChange();
    System.out.println(obj.coinChange(new int[] { 186, 419, 83, 408 }, 6249)); // 20
  }

  public int coinChange(int[] coins, int amount) {
    var max = amount + 1;
    var states = new int[max];
    for (int i = 1; i <= amount; ++i) {
      states[i] = max;
    }

    for (int i = 1; i <= amount; ++i) {
      for (int coin : coins) {
        if (coin <= i) {
          states[i] = Math.min(states[i], states[i - coin] + 1);
        }
      }
    }

    if (states[amount] != max) {
      return states[amount];
    }
    return -1;
  }
}
