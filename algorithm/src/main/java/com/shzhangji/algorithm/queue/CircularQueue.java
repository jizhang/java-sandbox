package com.shzhangji.algorithm.queue;

import java.util.NoSuchElementException;

public class CircularQueue {
  int[] elements;
  int head, tail;

  public CircularQueue(int maxSize) {
    elements = new int[maxSize + 1];
    head = tail = 0;
  }

  public boolean add(int element) {
    int pos = (tail + 1) % elements.length;
    if (pos == head) throw new IllegalStateException("Queue is full.");
    elements[tail] = element;
    tail = pos;
    return true;
  }

  public int remove() {
    if (head == tail) throw new NoSuchElementException();
    var value = elements[head];
    head = (head + 1) % elements.length;
    return value;
  }
}
