package com.shzhangji.algorithm.string;

import java.util.ArrayDeque;

// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
public class RemoveAdjacentDuplicates {
  public static void main(String[] args) {
    var obj = new RemoveAdjacentDuplicates();
    System.out.println(obj.removeDuplicates("abbaca")); // ca
    System.out.println(obj.removeDuplicates("azxxzy")); // ay

    System.out.println(obj.removeDuplicates("abbba")); // aba
  }

  public String removeDuplicates(String s) {
    return useStringBuilder(s);
  }

  String useArray(String s) {
    var arr = s.toCharArray();
    int end = -1;
    for (char c : arr) {
      if (end >= 0 && arr[end] == c) {
        --end;
      } else {
        arr[++end] = c;
      }
    }
    return String.valueOf(arr, 0, end + 1);
  }

  String useStack(String s) {
    var stack = new ArrayDeque<Character>();
    for (char c : s.toCharArray()) {
      if (!stack.isEmpty() && stack.peek() == c) {
        stack.pop();
      } else {
        stack.push(c);
      }
    }

    var sb = new StringBuilder();
    stack.forEach(sb::append);
    return sb.reverse().toString();
  }

  String useStringBuilder(String s) {
    var sb = new StringBuilder();
    for (char c : s.toCharArray()) {
      if (sb.length() > 0 && sb.charAt(sb.length() - 1) == c) {
        sb.deleteCharAt(sb.length() - 1);
      } else {
        sb.append(c);
      }
    }
    return sb.toString();
  }
}
