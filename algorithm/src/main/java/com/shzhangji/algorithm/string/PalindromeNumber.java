package com.shzhangji.algorithm.string;

// https://leetcode.com/problems/palindrome-number/
public class PalindromeNumber {
  public static void main(String[] args) {
    var obj = new PalindromeNumber();
    System.out.println(obj.isPalindrome(121)); // true
    System.out.println(obj.isPalindrome(-121)); // false
    System.out.println(obj.isPalindrome(10)); // false

    System.out.println(obj.isPalindrome(0)); // true
    System.out.println(obj.isPalindrome(11)); // true
  }

  // Follow up: Could you solve it without converting the integer to a string?
  public boolean isPalindrome(int x) {
    if (x < 0) return false;
    int result = 0, tmp = x;
    while (tmp > 0) {
      result = result * 10 + tmp % 10;
      tmp /= 10;
    }
    return result == x;
  }
}
