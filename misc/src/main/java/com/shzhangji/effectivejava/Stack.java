package com.shzhangji.effectivejava;

public interface Stack<E> {
  void push(E e);
  E pop();
  boolean isEmpty();
}
