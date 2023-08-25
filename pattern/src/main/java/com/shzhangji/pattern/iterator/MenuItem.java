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
    System.out.print("  " + name);
    if (vegetarian) {
      System.out.print("(v)");
    }
    System.out.println(", " + price);
    System.out.println("    -- " + description);
  }

  @Override
  public void add(MenuComponent menuComponent) {
    throw new UnsupportedOperationException();
  }
}
