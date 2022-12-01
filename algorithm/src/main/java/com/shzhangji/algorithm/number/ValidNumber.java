package com.shzhangji.algorithm.number;

// https://leetcode.com/problems/valid-number/
public class ValidNumber {
  public static void main(String[] args) {
    var obj = new ValidNumber();

    var trueCases = new String[] {
      "2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789",
      "0",
    };
    for (var s : trueCases) {
      if (!obj.isNumber(s)) throw new RuntimeException(s);
    }

    var falseCases = new String[] {
      "abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53",
      "e", ".",
    };
    for (var s : falseCases) {
      if (obj.isNumber(s)) throw new RuntimeException(s);
    }

    System.out.println("All cases passed.");
  }

  public boolean isNumber(String s) {
    return s.matches("[+|-]?([0-9]+\\.?|[0-9]*\\.[0-9]+)([eE][+|-]?[0-9]+)?$");
  }
}
