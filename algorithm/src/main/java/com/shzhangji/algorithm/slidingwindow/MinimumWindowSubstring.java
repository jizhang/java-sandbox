package com.shzhangji.algorithm.slidingwindow;

// https://leetcode.com/problems/minimum-window-substring/
// https://leetcode.com/problems/minimum-window-substring/discuss/934026/EXPLAINED-or-Easy-to-understand-code-with-comments-or-3ms
public class MinimumWindowSubstring {
  public static void main(String[] args) {
    var obj = new MinimumWindowSubstring();
    System.out.println(obj.minWindow("ADOBECODEBANC", "ABC")); // BANC
    System.out.println(obj.minWindow("a", "a")); // a
    System.out.println(obj.minWindow("a", "aa")); // ""
  }

  public String minWindow(String s, String t) {
    var map = new int[128];
    t.chars().forEach(c -> ++map[c]);

    int i = 0, len = s.length() + 1, foundChars = 0;
    var result = "";
    for (int j = 0; j < s.length(); ++j) {
      --map[s.charAt(j)];
      if (map[s.charAt(j)] >= 0) {
        ++foundChars;
      }

      while (foundChars == t.length()) {
        if (j - i + 1 < len) {
          len = j - i + 1;
          result = s.substring(i, j + 1);
        }

        ++map[s.charAt(i)];
        if (map[s.charAt(i)] > 0) {
          --foundChars;
        }
        ++i;
      }
    }

    return result;
  }
}
