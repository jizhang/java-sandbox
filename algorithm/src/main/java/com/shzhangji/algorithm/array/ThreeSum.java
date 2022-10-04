package com.shzhangji.algorithm.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// https://leetcode.com/problems/3sum/
public class ThreeSum {
  public static void main(String[] args) {
    var obj = new ThreeSum();
    System.out.println(obj.threeSum(new int[] { -1, 0, 1, 2, -1, -4 })); // [[-1, -1, 2], [-1, 0, 1]]
    System.out.println(obj.threeSum(new int[] { 0, 1, 1 })); // []
    System.out.println(obj.threeSum(new int[] { 0, 0, 0 })); // [[0, 0, 0]]
  }

  public List<List<Integer>> threeSum(int[] nums) {
    return twoPointers(nums);
  }

  List<List<Integer>> bruteForce(int[] nums) {
    Arrays.sort(nums);
    var result = new HashSet<List<Integer>>();
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i + 1; j < nums.length; ++j) {
        for (int k = j + 1; k < nums.length; ++k) {
          if (nums[i] + nums[j] + nums[k] == 0) {
            result.add(List.of(nums[i], nums[j], nums[k]));
          }
        }
      }
    }
    return List.copyOf(result);
  }

  // https://leetcode.com/problems/3sum/discuss/508631/Simple-O(n2)-Java-solution-with-explanations
  List<List<Integer>> twoPointers(int[] nums) {
    Arrays.sort(nums);
    var result = new HashSet<List<Integer>>();
    for (int i = 0; i < nums.length - 2; ++i) {
      int j = i + 1, k = nums.length - 1;
      while (j < k) {
        int sum = nums[i] + nums[j] + nums[k];
        if (sum == 0) {
          result.add(List.of(nums[i], nums[j], nums[k]));
          ++j;
          --k;
        } else if (sum > 0) { // Array is sorted.
          --k;
        } else {
          ++j;
        }
      }
    }
    return List.copyOf(result);
  }
}
