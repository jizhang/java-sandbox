package com.shzhangji.effectivejava.item29;

public interface Stack<E> {
  void push(E e);
  E pop();
  boolean isEmpty();
}
