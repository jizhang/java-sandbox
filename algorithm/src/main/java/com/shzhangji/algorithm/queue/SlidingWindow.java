package com.shzhangji.algorithm.queue;

import java.util.ArrayDeque;
import java.util.Arrays;

// https://leetcode.com/problems/sliding-window-maximum/
public class SlidingWindow {
  public static void main(String[] args) {
    var obj = new SlidingWindow();
    System.out.println(Arrays.toString(obj.maxSlidingWindow(new int[] { 1, 3, -1, -3 ,5, 3, 6, 7 }, 3))); // [3, 3, 5, 5, 6, 7]
    System.out.println(Arrays.toString(obj.maxSlidingWindow(new int[] { 1 }, 1))); // [1]
  }

  public int[] maxSlidingWindow(int[] nums, int k) {
    var result = new int[nums.length - k + 1];
    var deque = new ArrayDeque<Integer>(); // Store indices, not numbers.
    for (int i = 0; i < nums.length; ++i) {
      if (!deque.isEmpty() && deque.getFirst() == i - k) deque.removeFirst();
      while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) deque.removeLast();
      deque.addLast(i);
      if (i >= k - 1) {
        result[i - k + 1] = nums[deque.getFirst()];
      }
    }
    return result;
  }
}
