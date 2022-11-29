package com.shzhangji.algorithm.number;

import java.util.Arrays;

// https://leetcode.com/problems/multiply-strings/
// https://leetcode.com/problems/multiply-strings/discuss/1563541/Java-Easy-and-Clean-solution-w-detailed-example-or-Basic-Math-or-TC%3AO(MN)-SC%3AO(M%2BN)
public class MultiplyStrings {
  public static void main(String[] args) {
    var obj = new MultiplyStrings();
    System.out.println(obj.multiply("2", "3")); // 6
    System.out.println(obj.multiply("123", "456")); // 56088
  }

  public String multiply(String num1, String num2) {
    if ("0".equals(num1) || "0".equals(num2)) return "0";

    var arr = new int[num1.length() + num2.length()];
    for (int i = num1.length() - 1; i >= 0; --i) {
      for (int j = num2.length() - 1; j >= 0; --j) {
        var product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
        product += arr[i + j + 1];
        arr[i + j + 1] = product % 10;
        arr[i + j] += product / 10;
      }
    }

    int i = 0;
    while (arr[i] == 0) ++i;
    var sb = new StringBuilder();
    while (i < arr.length) {
      sb.append(arr[i]);
      ++i;
    }
    return sb.toString();
  }
}
