package com.shzhangji.pattern.factory;

public abstract class PizzaStore {
  public Pizza orderPizza(String type) {
    var pizza = createPizza(type);
    pizza.prepare();
    pizza.bake();
    pizza.cut();
    pizza.box();
    return pizza;
  }

  abstract Pizza createPizza(String type);
}
