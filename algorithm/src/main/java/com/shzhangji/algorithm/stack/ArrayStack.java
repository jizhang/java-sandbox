package com.shzhangji.algorithm.stack;

import java.util.NoSuchElementException;

public class ArrayStack {
  int[] elements;
  int size;

  public ArrayStack(int maxSize) {
    elements = new int[maxSize];
    size = 0;
  }

  public void push(int element) {
    if (size == elements.length) throw new IllegalStateException("Stack is full.");
    elements[size++] = element;
  }

  public int pop() {
    if (size == 0) throw new NoSuchElementException();
    return elements[--size];
  }
}
