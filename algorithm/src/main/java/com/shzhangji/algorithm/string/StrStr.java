package com.shzhangji.algorithm.string;

// https://leetcode.com/problems/implement-strstr/
public class StrStr {
  public static void main(String[] args) {
    var obj = new StrStr();
    System.out.println(obj.strStr("hello", "ll")); // 2
    System.out.println(obj.strStr("aaaaa", "bba")); // -1
    System.out.println(obj.strStr("mississippi", "mississippi")); // 0
  }

  public int strStr(String haystack, String needle) {
    for (int i = 0; i <= haystack.length() - needle.length(); ++i) {
      int j;
      for (j = 0; j < needle.length(); ++j) {
        if (haystack.charAt(i + j) != needle.charAt(j)) {
          break;
        }
      }
      if (j == needle.length()) {
        return i;
      }
    }
    return -1;
  }
}
