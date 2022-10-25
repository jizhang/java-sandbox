package com.shzhangji.algorithm.slidingwindow;

import java.util.HashMap;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/
// https://leetcode.com/problems/longest-substring-without-repeating-characters/discuss/1500874/Java-or-TC%3A-O(N)-or-SC%3A-O(1)-or-Sliding-Window-using-HashMap-and-Two-Pointers
public class LongestSubstring {
  public static void main(String[] args) {
    var obj = new LongestSubstring();
    System.out.println(obj.lengthOfLongestSubstring("abcabcbb")); // 3
    System.out.println(obj.lengthOfLongestSubstring("bbbbb")); // 1
    System.out.println(obj.lengthOfLongestSubstring("pwwkew")); // 3

    System.out.println(obj.lengthOfLongestSubstring("au")); // 2
  }

  public int lengthOfLongestSubstring(String s) {
    if (s.length() <= 1) return s.length();

    var map = new HashMap<Character, Integer>(); // Can be replaced with int[128].
    int i = 0, len = 0;
    for (int j = 0; j < s.length(); ++j) {
      if (map.containsKey(s.charAt(j))) {
        i = Math.max(i, map.get(s.charAt(j)) + 1);
      }
      map.put(s.charAt(j), j);
      len = Math.max(len, j - i + 1);
    }

    return len;
  }
}
