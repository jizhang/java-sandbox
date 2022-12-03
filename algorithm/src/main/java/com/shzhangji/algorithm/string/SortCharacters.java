package com.shzhangji.algorithm.string;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// https://leetcode.com/problems/sort-characters-by-frequency/
public class SortCharacters {
  public static void main(String[] args) {
    var obj = new SortCharacters();
    System.out.println(obj.frequencySort("tree")); // eert
    System.out.println(obj.frequencySort("cccaaa")); // aaaccc
    System.out.println(obj.frequencySort("Aabb")); // bbAa
  }

  public String frequencySort(String s) {
    var map = new HashMap<Character, Integer>();
    for (char c : s.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    return map.entrySet().stream()
      .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
      .map(entry -> String.valueOf(entry.getKey()).repeat(entry.getValue()))
      .collect(Collectors.joining());
  }
}
