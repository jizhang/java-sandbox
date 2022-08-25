package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/coin-change/
public class CoinChange {
  public static void main(String[] args) {
    var coins = new int[] { 186, 419, 83, 408 };
    int money = 6249;
    System.out.println("Coin count: " + coinChange1(coins, money));
    System.out.println("Coin count: " + coinChange2(coins, money));
    System.out.println("Coin count: " + coinChange3(coins, money));
  }

  static int coinChange1(int[] coins, int amount) {
    if (amount == 0) {
      return 0;
    }

    var states = new int[amount + 1];

    for (int coin : coins) {
      if (coin == amount) {
        return 1;
      }
      if (coin < amount) {
        states[coin] = 1;
      }
    }

    for (int i = 1; i < amount; ++i) {
      for (int j = amount; j >= 1; --j) {
        if (states[j] == i) {
          for (int coin : coins) {
            if (j + coin == amount) {
              return i + 1;
            }
            if (j + coin < amount) {
              states[j + coin] = i + 1;
            }
          }
        }
      }
    }

    return -1;
  }

  static int coinChange2(int[] coins, int amount) {
    return coinChange2(coins, amount, new int[amount + 1]);
  }

  static int coinChange2(int[] coins, int amount, int[] mem) {
    if (amount == 0) {
      return 0;
    }

    if (mem[amount] != 0) {
      return mem[amount];
    }

    int minCount = Integer.MAX_VALUE;
    for (int coin : coins) {
      if (coin <= amount) {
        int count = coinChange2(coins, amount - coin, mem);
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

  static int coinChange3(int[] coins, int amount) {
    var max = amount + 1;
    var mem = new int[max];
    for (int i = 1; i <= amount; ++i) {
      mem[i] = max;
    }

    for (int i = 1; i <= amount; ++i) {
      for (int coin : coins) {
        if (coin <= i) {
          mem[i] = Math.min(mem[i], mem[i - coin] + 1);
        }
      }
    }

    if (mem[amount] != max) {
      return mem[amount];
    }
    return -1;
  }
}
