package com.shzhangji.algorithm.string;

// https://leetcode.com/problems/valid-palindrome/
public class ValidPalindrome {
  public static void main(String[] args) {
    var obj = new ValidPalindrome();
    System.out.println(obj.isPalindromeWhile("abc"));
    System.out.println(obj.isPalindromeWhile("hannah"));
    System.out.println(obj.isPalindromeWhile("Nice hat, Bob Tahecin."));
    System.out.println(obj.isPalindromeWhile(" "));
  }

  public boolean isPalindrome(String s) {
    var chars = s.toCharArray();
    int j = 0;
    for (int i = 0; i < s.length(); ++i) {
      if (chars[i] >= '0' && chars[i] <= '9') {
        chars[j++] = chars[i];
      } else if (chars[i] >= 'A' && chars[i] <= 'Z') {
        chars[j++] = (char) (chars[i] - 'A' + 'a');
      } else if (chars[i] >= 'a' && chars[i] <= 'z') {
        chars[j++] = chars[i];
      }
    }

    for (int i = 0; i < j / 2; ++i) {
      if (chars[i] != chars[j - i - 1]) {
        return false;
      }
    }
    return true;
  }

  public boolean isPalindromeWhile(String s) {
    int left = 0;
    int right = s.length() - 1;
    while (left < right) {
      while (left < right && !Character.isLetterOrDigit(s.charAt(left))) ++left;
      while (left < right && !Character.isLetterOrDigit(s.charAt(right))) --right;
      if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
        return false;
      }
      ++left;
      --right;
    }
    return true;
  }
}
