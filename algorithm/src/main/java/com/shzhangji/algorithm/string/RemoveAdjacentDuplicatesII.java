package com.shzhangji.algorithm.string;

import java.util.ArrayDeque;

// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
public class RemoveAdjacentDuplicatesII {
  public static void main(String[] args) {
    var obj = new RemoveAdjacentDuplicatesII();
    System.out.println(obj.removeDuplicates("abcd", 2)); // abcd
    System.out.println(obj.removeDuplicates("deeedbbcccbdaa", 3)); // aa
    System.out.println(obj.removeDuplicates("pbbcggttciiippooaais", 2)); // ps
  }

  public String removeDuplicates(String s, int k) {
    var stack = new ArrayDeque<char[]>();

    for (char c : s.toCharArray()) {
      if (!stack.isEmpty() && stack.peek()[0] == c) {
        if (stack.peek()[1] == k - 1) {
          stack.pop();
        } else {
          stack.peek()[1] += 1;
        }
      } else {
        stack.push(new char[] { c, 1 });
      }
    }

    var sb = new StringBuilder();
    for (var t : stack) {
      while (t[1]-- > 0) sb.append(t[0]);
    }
    return sb.reverse().toString();
  }
}
