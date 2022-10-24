package com.shzhangji.algorithm.string;

// https://leetcode.com/problems/add-binary/
public class AddBinary {
  public static void main(String[] args) {
    var obj = new AddBinary();
    System.out.println(obj.addBinary("11", "1")); // 100
    System.out.println(obj.addBinary("1010", "1011")); // 10101
  }

  public String addBinary(String a, String b) {
    var sb = new StringBuilder();

    int i = a.length() - 1, j = b.length() - 1;
    int carry = 0;
    while (i >= 0 || j >= 0) {
      int na = i >= 0 ? (a.charAt(i) - '0') : 0;
      int nb = j >= 0 ? (b.charAt(j) - '0') : 0;
      int res = na + nb + carry;

      if (res <= 1) {
        carry = 0;
        sb.append(res);
      } else {
        carry = 1;
        sb.append(res % 2);
      }
      // Or
      // carry = res / 2;
      // sb.append(res % 2);

      --i;
      --j;
    }

    if (carry == 1) sb.append("1");
    return sb.reverse().toString();
  }
}
