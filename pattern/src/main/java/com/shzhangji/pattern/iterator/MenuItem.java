package com.shzhangji.pattern.iterator;

import lombok.Value;

@Value
public class MenuItem implements MenuComponent {
  String name;
  String description;
  boolean vegetarian;
  double price;

  @Override
  public void print() {
    System.out.print("  " + getName());
    if (isVegetarian()) {
      System.out.print("(v)");
    }
    System.out.println(", " + getPrice());
    System.out.println("    -- " + getDescription());
  }

  @Override
  public void add(MenuComponent menuComponent) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterator<MenuComponent> createIterator() {
    return new NullIterator();
  }
}
