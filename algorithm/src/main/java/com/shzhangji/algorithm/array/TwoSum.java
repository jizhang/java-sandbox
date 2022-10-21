package com.shzhangji.algorithm.array;

import java.util.Arrays;
import java.util.HashMap;

// https://leetcode.com/problems/two-sum/
public class TwoSum {
  public static void main(String[] args) {
    var obj = new TwoSum();
    System.out.println(Arrays.toString(obj.twoSum(new int[] { 2, 7, 11, 15 }, 9))); // [0, 1]
    System.out.println(Arrays.toString(obj.twoSum(new int[] { 3, 2, 4 }, 6))); // [1, 2]
    System.out.println(Arrays.toString(obj.twoSum(new int[] { 3, 3 }, 6))); // [0, 1]
  }

  // Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
  public int[] twoSum(int[] nums, int target) {
    var map = new HashMap<Integer, Integer>();
    for (int i = 0; i < nums.length; ++i) {
      int key = target - nums[i];
      if (map.containsKey(key)) {
        return new int[] { i, map.get(key) };
      }
      map.put(nums[i], i);
    }
    return new int[0];
  }
}
