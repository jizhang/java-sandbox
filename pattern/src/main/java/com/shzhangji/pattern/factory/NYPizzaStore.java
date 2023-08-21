package com.shzhangji.pattern.factory;

public class NYPizzaStore extends PizzaStore {
  @Override
  Pizza createPizza(String type) {
    if ("cheese".equals(type)) {
      return new NYStyleCheesePizza();
    }
    return null;
  }
}
