package com.shzhangji.pattern.iterator;

public class DinerMenuIterator implements Iterator<MenuItem> {
  MenuItem[] items;
  int numberOfItems;
  int position = 0;

  public DinerMenuIterator(MenuItem[] items, int numberOfItems) {
    this.items = items;
    this.numberOfItems = numberOfItems;
  }

  @Override
  public boolean hasNext() {
    return position < numberOfItems;
  }

  @Override
  public MenuItem next() {
    var item = items[position];
    ++position;
    return item;
  }
}
