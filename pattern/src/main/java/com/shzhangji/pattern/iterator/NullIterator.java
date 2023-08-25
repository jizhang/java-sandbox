package com.shzhangji.pattern.iterator;

public class NullIterator implements Iterator<MenuComponent> {
  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public MenuComponent next() {
    return null;
  }
}
