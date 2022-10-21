package com.shzhangji.algorithm.array;

import java.util.HashSet;

// https://leetcode.com/problems/contains-duplicate/
public class ContainsDuplicate {
  public static void main(String[] args) {
    var obj = new ContainsDuplicate();
    System.out.println(obj.containsDuplicate(new int[] { 1, 2, 3, 1 })); // true
    System.out.println(obj.containsDuplicate(new int[] { 1, 2, 3, 4 })); // false
    System.out.println(obj.containsDuplicate(new int[] { 1, 1, 1, 3, 3, 4, 3, 2, 4, 2 })); // true
  }

  public boolean containsDuplicate(int[] nums) {
    var set = new HashSet<Integer>();
    for (int num : nums) {
      if (!set.add(num)) {
        return true;
      }
    }
    return false;
  }
}
