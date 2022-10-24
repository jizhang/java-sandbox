package com.shzhangji.algorithm.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/find-median-from-data-stream/
public class MedianFinder {
  public static void main(String[] args) {
    MedianFinder medianFinder = new MedianFinder();
    medianFinder.addNum(1);    // arr = [1]
    medianFinder.addNum(2);    // arr = [1, 2]
    System.out.println(medianFinder.findMedian()); // return 1.5 (i.e., (1 + 2) / 2)
    medianFinder.addNum(3);    // arr[1, 2, 3]
    System.out.println(medianFinder.findMedian()); // return 2.0
  }

  PriorityQueue<Integer> maxHeap;
  PriorityQueue<Integer> minHeap;

  public MedianFinder() {
    maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    minHeap = new PriorityQueue<>();
  }

  public void addNum(int num) {
    if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
      maxHeap.add(num);
    } else {
      minHeap.add(num);
    }

    if (maxHeap.size() - minHeap.size() > 1) {
      minHeap.add(maxHeap.remove());
    } else if (minHeap.size() > maxHeap.size()) {
      maxHeap.add(minHeap.remove());
    }
  }

  public double findMedian() {
    if (maxHeap.isEmpty()) return 0;

    if (maxHeap.size() == minHeap.size()) {
      return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
    return maxHeap.peek();
  }
}
