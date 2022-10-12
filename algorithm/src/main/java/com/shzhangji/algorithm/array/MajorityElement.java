package com.shzhangji.algorithm.array;

import java.util.HashMap;

// https://leetcode.com/problems/majority-element/
public class MajorityElement {
  public static void main(String[] args) {
    var obj = new MajorityElement();
    System.out.println(obj.majorityElement(new int[] { 3, 2, 3 })); // 3
    System.out.println(obj.majorityElement(new int[] { 2, 2, 1, 1, 1, 2, 2 })); // 2
  }

  public int majorityElement(int[] nums) {
    var map = new HashMap<Integer, Integer>();
    for (int n : nums) {
      int count = map.getOrDefault(n, 0) + 1;
      if (count > nums.length / 2) return n;
      map.put(n, count);
    }
    return 0;
  }
}
