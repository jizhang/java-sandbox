package com.shzhangji.algorithm.queue;

import java.util.NoSuchElementException;

public class ArrayQueue {
  int[] elements;
  int head;
  int tail; // Next empty slot.

  public ArrayQueue(int maxSize) {
    elements = new int[maxSize];
    head = tail = 0;
  }

  /**
   * @see java.util.Queue#add
   */
  public boolean add(int element) {
    if (tail == elements.length) {
      if (head == 0) throw new IllegalStateException("Queue is full.");
      int size = tail - head;
      for (int i = 0; i < size; ++i) {
        elements[i] = elements[i + 1];
      }
      head = 0;
      tail = size;
    }
    elements[tail++] = element;
    return true;
  }

  /**
   * @see java.util.Queue#remove
   */
  public int remove() {
    if (head == tail) throw new NoSuchElementException();
    return elements[head++];
  }
}
