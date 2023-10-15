package com.shzhangji.effectivejava.item18;

import lombok.Getter;

import java.util.Collection;
import java.util.Set;

public class InstrumentedSet<E> extends ForwardingSet<E> {
  @Getter
  private int addCount = 0;

  public InstrumentedSet(Set<E> s) {
    super(s);
  }

  @Override
  public boolean add(E e) {
    ++addCount;
    return super.add(e);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    addCount += c.size();
    return super.addAll(c);
  }
}
