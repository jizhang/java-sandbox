package com.shzhangji.algorithm.string;

import java.util.ArrayList;

// https://leetcode.com/problems/reverse-words-in-a-string/
public class ReverseWords {
  public static void main(String[] args) {
    var obj = new ReverseWords();
    System.out.println(obj.reverseWords("the sky is blue"));
    System.out.println(obj.reverseWords("  hello world  "));
    System.out.println(obj.reverseWords("a good   example"));
  }

  public String reverseWords(String s) {
    var words = new ArrayList<String>();
    var word = new StringBuilder();
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == ' ') {
        if (word.length() > 0) {
          words.add(word.toString());
          word = new StringBuilder();
        }
      } else {
        word.append(s.charAt(i));
      }
    }

    if (word.length() > 0) {
      words.add(word.toString());
    }

    var result = new StringBuilder();
    for (int i = words.size() - 1; i >= 0; --i) {
      result.append(words.get(i));
      if (i > 0) {
        result.append(" ");
      }
    }

    return result.toString();
  }
}
