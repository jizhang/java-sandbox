package com.shzhangji.algorithm.slidingwindow;

import java.util.HashSet;

// https://leetcode.com/problems/contains-duplicate-ii/
public class ContainsDuplicateII {
  public static void main(String[] args) {
    var obj = new ContainsDuplicateII();
    System.out.println(obj.containsNearbyDuplicate(new int[] { 1, 2, 3, 1 }, 3)); // true
    System.out.println(obj.containsNearbyDuplicate(new int[] { 1, 0, 1, 1 }, 1)); // true
    System.out.println(obj.containsNearbyDuplicate(new int[] { 1, 2, 3, 1, 2, 3 }, 2)); // false
  }

  public boolean containsNearbyDuplicate(int[] nums, int k) {
    var set = new HashSet<Integer>();
    int i = 0;
    for (int j = 0; j < nums.length; ++j) {
      if (j - i > k) {
        set.remove(nums[i]);
        ++i;
      }

      if (set.contains(nums[j])) {
        return true;
      }

      set.add(nums[j]);
    }
    return false;
  }
}
