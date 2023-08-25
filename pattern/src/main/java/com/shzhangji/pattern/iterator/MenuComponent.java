package com.shzhangji.pattern.iterator;

public interface MenuComponent {
  String getName();
  String getDescription();
  boolean isVegetarian();
  double getPrice();
  void print();
  void add(MenuComponent menuComponent);
  Iterator<MenuComponent> createIterator();
}
