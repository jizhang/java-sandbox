package com.shzhangji.algorithm.dp;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
public class Stock {
  public static void main(String[] args) {
    var obj = new Stock();
    System.out.println(obj.maxProfit(new int[] { 7, 1, 5, 3, 6, 4 })); // 5
    System.out.println(obj.maxProfit(new int[] { 7, 6, 4, 3, 1 })); // 0
  }

  public int maxProfit(int[] prices) {
    int minPrice = prices[0];
    int maxProfit = 0;
    for (int price : prices) {
      if (price < minPrice) {
        minPrice = price;
      }
      if (price - minPrice > maxProfit) {
        maxProfit = price - minPrice;
      }
    }
    return maxProfit;
  }
}
