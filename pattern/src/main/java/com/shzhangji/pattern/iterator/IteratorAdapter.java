package com.shzhangji.pattern.iterator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IteratorAdapter<T> implements Iterator<T> {
  final java.util.Iterator<T> iterator;

  @Override
  public boolean hastNext() {
    return iterator.hasNext();
  }

  @Override
  public T next() {
    return iterator.next();
  }
}
