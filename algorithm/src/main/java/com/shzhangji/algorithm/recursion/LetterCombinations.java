package com.shzhangji.algorithm.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/
public class LetterCombinations {
  public static void main(String[] args) {
    var obj = new LetterCombinations();
    System.out.println(obj.letterCombinations("23")); // ["ad","ae","af","bd","be","bf","cd","ce","cf"]
    System.out.println(obj.letterCombinations("")); // []
    System.out.println(obj.letterCombinations("2")); // ["a","b","c"]
  }

  final Map<Character, String> map = Map.of(
    '2', "abc",
    '3', "def",
    '4', "ghi",
    '5', "jkl",
    '6', "mno",
    '7', "pqrs",
    '8', "tuv",
    '9', "wxyz");

  public List<String> letterCombinations(String digits) {
    return recur(digits, 0);
  }

  List<String> recur(String digits, int i) {
    if (i == digits.length()) return List.of();

    var child = recur(digits, i + 1);
    var result = new ArrayList<String>();
    for (char c : map.get(digits.charAt(i)).toCharArray()) {
      if (child.isEmpty()) result.add(String.valueOf(c));
      else child.forEach(s -> result.add(c + s));
    }

    return result;
  }
}
