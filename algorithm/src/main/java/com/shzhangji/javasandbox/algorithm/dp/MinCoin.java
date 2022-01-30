package com.shzhangji.javasandbox.algorithm.dp;

public class MinCoin {
  public static void main(String[] args) {
    var coins = new int[] { 186, 419, 83, 408 };
    int money = 6249;
    System.out.println("Coin count: " + minCoin(coins, money));
  }

  static int minCoin(int[] coins, int amount) {
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
}
