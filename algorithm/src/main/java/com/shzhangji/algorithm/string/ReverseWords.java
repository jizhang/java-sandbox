package com.shzhangji.algorithm.string;

import java.util.ArrayList;

// https://leetcode.com/problems/reverse-words-in-a-string/
public class ReverseWords {
  public static void main(String[] args) {
    var obj = new ReverseWords();
    System.out.println(obj.reverseWords("the sky is blue"));
    System.out.println(obj.reverseWords("  hello world  "));
    System.out.println(obj.reverseWords("a good   example"));

    System.out.println(obj.reverseWords("a"));
  }

  public String reverseWords(String s) {
    return withCharArray(s);
  }

  String withStringBuilder(String s) {
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

  // Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
  String withCharArray(String s) {
    var arr = s.toCharArray();

    int start = 0, end = 1, i = 0;
    while (i < arr.length) {
      if (arr[i] == ' ') {
        ++i;
        continue;
      }

      end = start;
      int j = i;
      while (j < arr.length && arr[j] != ' ') arr[end++] = arr[j++];
      reverse(arr, start, end - 1);
      if (j == arr.length) break;

      arr[end] = ' ';
      start = end + 1;
      i = j + 1;
    }

    reverse(arr, 0, end - 1);
    return new String(arr, 0, end);
  }

  void reverse(char[] arr, int i, int j) {
    while (i < j) {
      char t = arr[i];
      arr[i++] = arr[j];
      arr[j--] = t;
    }
  }
}
