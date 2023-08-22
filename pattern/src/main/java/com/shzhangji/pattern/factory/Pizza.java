package com.shzhangji.pattern.factory;

import com.shzhangji.pattern.factory.ingredient.*;
import lombok.Getter;
import lombok.Setter;

public abstract class Pizza {
  @Getter @Setter
  String name;
  Dough dough;
  Sauce sauce;
  Veggies[] veggies;
  Cheese cheese;
  Pepperoni pepperoni;
  Clams clam;

  abstract void prepare();

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
