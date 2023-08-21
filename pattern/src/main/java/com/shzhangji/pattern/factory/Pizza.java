package com.shzhangji.pattern.factory;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {
  @Getter
  String name;
  String dough;
  String sauce;
  List<String> toppings = new ArrayList<>();

  void prepare() {
    System.out.println("Preparing " + name);
    System.out.println("Tossing dough...");
    System.out.println("Adding sauce...");
    System.out.println("Adding toppings:");
    for (var topping : toppings) {
      System.out.println("   " + topping);
    }
  }

  void bake() {
    System.out.println("Bake for 25 minutes at 350");
  }

  void cut() {
    System.out.println("Cutting the pizza into diagonal slices");
  }

  void box() {
    System.out.println("Place pizza in official PizzaStore box");
  }
}
