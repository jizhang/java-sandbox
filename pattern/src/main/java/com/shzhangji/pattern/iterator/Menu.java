package com.shzhangji.pattern.iterator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Menu implements MenuComponent {
  @Getter
  final String name;
  @Getter
  final String description;
  List<MenuComponent> menuComponents = new ArrayList<>();

  @Override
  public boolean isVegetarian() {
    throw new UnsupportedOperationException();
  }

  @Override
  public double getPrice() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void print() {
    System.out.print("\n" + name);
    System.out.println(", " + description);
    System.out.println("----------------");

    menuComponents.forEach(MenuComponent::print);
  }

  @Override
  public void add(MenuComponent menuComponent) {
    menuComponents.add(menuComponent);
  }
}
