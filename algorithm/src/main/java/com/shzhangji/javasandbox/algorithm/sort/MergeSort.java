package com.shzhangji.javasandbox.algorithm.sort;

import java.util.Arrays;

public class MergeSort {
  public static void main(String[] args) {
    testSort(new int[0]);
    testSort(new int[] {1});
    testSort(new int[] {2, 1});
    testSort(new int[] {2, 1, 3});
    testSort(new int[] {2, 1, 3, 9});
    testSort(new int[] {2, 1, 3, 9, 5});
  }

  static void testSort(int[] a) {
    int[] b = Arrays.copyOf(a, a.length);
    sort(a);
    Arrays.sort(b);
    if (!Arrays.equals(a, b)) {
      System.out.printf("Expected: %s Actual: %s%n", Arrays.toString(b), Arrays.toString(a));
    }
  }

  static void sort(int[] a) {
    sort0(a, 0, a.length - 1);
  }

  static void sort0(int[] a, int p, int r) {
    if (p >= r) return;
    int q = (p + r) / 2;
    sort0(a, p, q);
    sort0(a, q + 1, r);
    merge(a, p, q, r);
  }

  static void merge(int[] a, int p, int q, int r) {
    int i = p, j = q + 1, k = 0;
    int[] tmp = new int[r - p + 1];
    while (i <= q && j <= r) {
      if (a[i] <= a[j]) {
        tmp[k++] = a[i++];
      } else {
        tmp[k++] = a[j++];
      }
    }

    int start = i, end = q;
    if (j <= r) {
      start = j;
      end = r;
    }

    while (start <= end) {
      tmp[k++] = a[start++];
    }

    for (i = 0; i <= r - p; ++i) {
      a[p + i] = tmp[i];
    }
  }
}
