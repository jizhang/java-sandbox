package com.shzhangji.javasandbox.algorithm.dp;

public class MinCoin {
  public static void main(String[] args) {
    var coins = new int[] { 186, 419, 83, 408 };
    int money = 6249;
    System.out.println("Coin count: " + minCoin(coins, money));
  }

  static int minCoin(int[] coins, int money) {
    var states = new boolean[money + 1];

    states[0] = true;
    for (int coin : coins) {
      states[coin] = true;
    }

    for (int i = 1; i < states.length; ++i) {
      for (int j = money; j >= 1; --j) {
        if (states[j]) {
          for (int coin : coins) {
            if (j + coin == money) {
              return i + 1;
            }
            if (j + coin < money) {
              states[j + coin] = true;
            }
          }
        }
      }
    }

    return 0;
  }
}
