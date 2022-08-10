package com.shzhangji.algorithm.string;

// https://leetcode.com/problems/reverse-string/
public class ReverseString {
  public static void main(String[] args) {
    var obj = new ReverseString();
    char[] s = "hello".toCharArray();
    obj.reverseString(s);
    System.out.println(String.valueOf(s));

    s = "Hannah".toCharArray();
    obj.reverseString(s);
    System.out.println(String.valueOf(s));
  }

  public void reverseString(char[] s) {
    for (int i = 0; i < s.length / 2; ++i) {
      char t = s[i];
      s[i] = s[s.length - 1 - i];
      s[s.length - 1 - i] = t;
    }
  }
}
