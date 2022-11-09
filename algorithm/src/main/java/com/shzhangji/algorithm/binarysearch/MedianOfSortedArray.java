package com.shzhangji.algorithm.binarysearch;

// https://leetcode.com/problems/median-of-two-sorted-arrays/
public class MedianOfSortedArray {
  public static void main(String[] args) {
    var obj = new MedianOfSortedArray();
    System.out.println(obj.findMedianSortedArrays(new int[] { 1, 3 }, new int[] { 2 })); // 2.0
    System.out.println(obj.findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 3, 4 })); // 2.5

    System.out.println(obj.findMedianSortedArrays(new int[] { 100000 }, new int[] { 100001 })); // 100000.5
    System.out.println(obj.findMedianSortedArrays(new int[] { 1, 5, 6, 7 }, new int[] { 2, 3, 4, 8 })); // 4.5
  }

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    return binarySearch(nums1, nums2);
  }

  double bruteForce(int[] nums1, int[] nums2) {
    int len = nums1.length + nums2.length;
    int i = 0, j = 0;
    int prev = 0, current = 0;

    for (int k = 0; k <= len / 2; ++k) {
      prev = current;
      if (i == nums1.length) {
        current = nums2[j++];
      } else if (j == nums2.length) {
        current = nums1[i++];
      } else if (nums1[i] < nums2[j]) {
        current = nums1[i++];
      } else {
        current = nums2[j++];
      }
    }

    if (len % 2 == 0) {
      return (prev + current) / 2.0;
    }
    return current;
  }

  // The overall run time complexity should be O(log (m+n))
  // https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/1701107/Java-Binary-Search-Explained
  double binarySearch(int[] nums1, int[] nums2) {
    int m = nums1.length, n = nums2.length;
    if (m > n) return binarySearch(nums2, nums1);

    int i = 0, j = m; // Sentinels around nums1 and nums2.
    while (i <= j) {
      int mid = i + (j - i) / 2;
      int k = (m + n) / 2 - mid;

      int shortLeft = mid == 0 ? Integer.MIN_VALUE : nums1[mid - 1];
      int shortRight = mid == m ? Integer.MAX_VALUE : nums1[mid];
      int longLeft = k == 0 ? Integer.MIN_VALUE : nums2[k - 1];
      int longRight = k == n ? Integer.MAX_VALUE : nums2[k];

      if (shortLeft <= longRight && shortRight >= longLeft) {
        if ((m + n) % 2 == 0) {
          return (Math.max(shortLeft, longLeft) + Math.min(shortRight, longRight)) / 2.0;
        }
        return Math.min(shortRight, longRight);
      }

      if (nums1[mid] < nums2[k - 1]) {
        i = mid + 1;
      } else {
        j = mid - 1;
      }
    }

    return 0;
  }
}
