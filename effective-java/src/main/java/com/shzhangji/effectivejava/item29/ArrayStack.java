package com.shzhangji.effectivejava.item29;

import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<E> implements Stack<E> {
  private E[] elements;
  private int size = 0;
  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  @SuppressWarnings("unchecked")
  public ArrayStack(int initialCapacity) {
    elements = (E[]) new Object[initialCapacity];
  }

  public ArrayStack() {
    this(DEFAULT_INITIAL_CAPACITY);
  }

  @Override
  public void push(E e) {
    ensureCapacity();
    elements[size++] = e;
  }

  @Override
  public E pop() {
    if (size == 0) {
      throw new EmptyStackException();
    }
    E e = elements[--size];
    elements[size] = null;
    return e;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  private void ensureCapacity() {
    if (elements.length == size) {
      elements = Arrays.copyOf(elements, elements.length * 2 + 1);
    }
  }
}
