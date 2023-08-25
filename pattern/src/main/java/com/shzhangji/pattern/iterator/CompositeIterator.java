package com.shzhangji.pattern.iterator;

import java.util.ArrayDeque;
import java.util.Deque;

public class CompositeIterator implements Iterator<MenuComponent> {
  Deque<Iterator<MenuComponent>> stack = new ArrayDeque<>();

  public CompositeIterator(Iterator<MenuComponent> iterator) {
    stack.push(iterator);
  }

  @Override
  public boolean hasNext() {
    if (stack.isEmpty()) return false;
    if (stack.peek().hasNext()) return true;
    stack.pop();
    return hasNext();
  }

  @Override
  public MenuComponent next() {
    var item = stack.peek().next();
    if (item instanceof Menu) {
      stack.push(item.createIterator());
    }
    return item;
  }
}
