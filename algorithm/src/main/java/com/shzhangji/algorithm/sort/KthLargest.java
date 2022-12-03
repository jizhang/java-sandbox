package com.shzhangji.algorithm.sort;

// https://leetcode.com/problems/kth-largest-element-in-an-array/
// O(n)
public class KthLargest {
  public static void main(String[] args) {
    var obj = new KthLargest();
    System.out.println(obj.findKthLargest(new int[] { 3, 2, 1, 5, 6, 4 }, 2)); // 5
    System.out.println(obj.findKthLargest(new int[] { 3, 2, 3, 1, 2, 4, 5, 5, 6 }, 4)); // 4
  }

  public int findKthLargest(int[] nums, int k) {
    return quickSelect(nums, 0, nums.length - 1, k);
  }

  int quickSelect(int[] nums, int p, int r, int k) {
    int q = partition(nums, p, r);
    if (q == nums.length - k) return nums[q];
    if (q > nums.length - k) return quickSelect(nums, p, q - 1, k);
    return quickSelect(nums, q + 1, r, k);
  }

  int partition(int[] nums, int p, int r) {
    int i = p, j = p;
    while (j < r) {
      if (nums[j] < nums[r]) {
        swap(nums, i, j);
        ++i;
        ++j;
      } else {
        ++j;
      }
    }
    swap(nums, i, r);
    return i;
  }

  void swap(int[] nums, int a, int b) {
    int t = nums[a];
    nums[a] = nums[b];
    nums[b] = t;
  }
}
