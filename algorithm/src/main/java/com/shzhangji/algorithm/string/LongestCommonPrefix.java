package com.shzhangji.algorithm.string;

// https://leetcode.com/problems/longest-common-prefix/
public class LongestCommonPrefix {
  public static void main(String[] args) {
    var obj = new LongestCommonPrefix();
    System.out.println(obj.longestCommonPrefix(new String[] {
      "flower", "flow", "flight",
    })); // "fl"
    System.out.println(obj.longestCommonPrefix(new String[] {
      "dog", "racecar", "car",
    })); // ""
  }

  public String longestCommonPrefix(String[] strs) {
    for (int i = 0; i < strs[0].length(); ++i) {
      for (int j = 1; j < strs.length; ++j) {
        if (strs[j].length() == i || strs[j].charAt(i) != strs[0].charAt(i)) {
          return strs[0].substring(0, i);
        }
      }
    }
    return strs[0];
  }
}
