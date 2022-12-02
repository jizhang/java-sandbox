package com.shzhangji.algorithm.string;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// https://leetcode.com/problems/determine-if-two-strings-are-close/
public class CloseStrings {
  public static void main(String[] args) {
    var obj = new CloseStrings();
    System.out.println(obj.closeStrings("abc", "bca")); // true
    System.out.println(obj.closeStrings("a", "aa")); // false
    System.out.println(obj.closeStrings("cabbba", "abbccc")); // true
  }

  public boolean closeStrings(String word1, String word2) {
    var map1 = countChars(word1);
    var map2 = countChars(word2);
    if (!map1.keySet().equals(map2.keySet())) return false;
    var freq1 = map1.values().stream().sorted().collect(Collectors.toList());
    var freq2 = map2.values().stream().sorted().collect(Collectors.toList());
    return freq1.equals(freq2);
  }

  Map<Character, Integer> countChars(String s) {
    var map = new HashMap<Character, Integer>();
    for (char c : s.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    return map;
  }
}
