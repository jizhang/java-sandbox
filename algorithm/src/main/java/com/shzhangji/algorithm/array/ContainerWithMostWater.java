package com.shzhangji.algorithm.array;

// https://leetcode.com/problems/container-with-most-water/
// https://leetcode.com/problems/container-with-most-water/discuss/1069746/JS-Python-Java-C%2B%2B-or-2-Pointer-Solution-w-Visual-Explanation-or-beats-100
// Two point sliding window solution.
public class ContainerWithMostWater {
  public static void main(String[] args) {
    var obj = new ContainerWithMostWater();
    System.out.println(obj.maxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 })); // 49
    System.out.println(obj.maxArea(new int[] { 1, 1 })); // 1
  }

  public int maxArea(int[] height) {
    int result = 0;
    int i = 0, j = height.length - 1;
    while (i < j) {
      int area;
      if (height[i] <= height[j]) {
        area = height[i] * (j - i);
        ++i;
      } else {
        area = height[j] * (j - i);
        --j;
      }
      if (area > result) result = area;
    }
    return result;
  }
}
