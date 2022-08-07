package com.shzhangji.algorithm.heap;

// https://leetcode.com/problems/kth-largest-element-in-a-stream/
public class KthLargest {
  public static void main(String[] args) {
    var obj = new KthLargest(3, new int[] { 4, 5, 8, 2 });
    System.out.println(obj.add(3));   // return 4
    System.out.println(obj.add(5));   // return 5
    System.out.println(obj.add(10));  // return 5
    System.out.println(obj.add(9));   // return 8
    System.out.println(obj.add(4));   // return 8
  }

  int[] heap;
  int maxCount;
  int count;

  public KthLargest(int k, int[] nums) {
    heap = new int[k + 2];
    maxCount = k;
    count = 0;
    for (int num : nums) {
      add(num);
    }
  }

  public int add(int val) {
    heap[++count] = val;
    for (int i = count; i >= 1; i /= 2) {
      if (i / 2 >= 1 && heap[i] < heap[i / 2]) {
        swap(i, i / 2);
      }
    }

    if (count > maxCount) {
      heap[1] = heap[count--];
      heapify(1);
    }

    return heap[1];
  }

  void heapify(int i) {
    while (true) {
      int left = i * 2;
      int right = i * 2 + 1;
      int pos = i;
      if (left <= count && heap[pos] > heap[left]) {
        pos = left;
      }
      if (right <= count && heap[pos] > heap[right]) {
        pos = right;
      }
      if (i == pos) {
        break;
      }

      swap(i, pos);
      i = pos;
    }
  }

  void swap(int a, int b) {
    int t = heap[a];
    heap[a] = heap[b];
    heap[b] = t;
  }
}
