package com.shzhangji.algorithm.hashtable;

import java.util.Arrays;
import java.util.HashMap;

// https://leetcode.com/problems/two-sum/
public class TwoSum {
  public static void main(String[] args) {
    var nums = new int[] { 2, 7, 11, 15 };
    int target = 9;
    var result = new TwoSum().twoSum(nums, target);
    System.out.println(Arrays.toString(result));
  }

  public int[] twoSum(int[] nums, int target) {
    var numIndex = new HashMap<Integer, Integer>(nums.length);
    for (int i = 0; i < nums.length; ++i) {
      if (numIndex.containsKey(target - nums[i])) {
        return new int[] { i, numIndex.get(target - nums[i]) };
      }
      numIndex.put(nums[i], i);
    }
    return new int[] { -1, -1 };
  }
}
