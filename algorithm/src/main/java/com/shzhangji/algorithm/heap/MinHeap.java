package com.shzhangji.algorithm.heap;

import java.util.NoSuchElementException;

public class MinHeap {
  int[] elements;
  int size;

  public MinHeap(int maxSize) {
    elements = new int[maxSize + 1]; // elements[0] is not used, for easy calculation of index.
    size = 0;
  }

  public boolean add(int element) {
    if (size == elements.length - 1) throw new IllegalStateException("Heap is full.");
    elements[++size] = element;

    // Heapify from bottom to top.
    int i = size;
    while (true) {
      int parent = i / 2;
      if (parent >= 1 && elements[i] < elements[parent]) {
        swap(i, parent);
        i = parent;
      } else {
        break;
      }
    }

    return true;
  }

  public int remove() {
    if (size == 0) throw new NoSuchElementException();
    var value = elements[1];
    elements[1] = elements[size--];

    // Heapify from top to bottom.
    int i = 1;
    while (true) {
      int left = i * 2;
      int right = i * 2 + 1;
      int minPos = i;
      if (left <= size && elements[left] < elements[minPos]) minPos = left;
      if (right <= size && elements[right] < elements[minPos]) minPos = right;
      if (i == minPos) break;
      swap(i, minPos);
      i = minPos;
    }

    return value;
  }

  void swap(int a, int b) {
    int t = elements[a];
    elements[a] = elements[b];
    elements[b] = t;
  }
}
