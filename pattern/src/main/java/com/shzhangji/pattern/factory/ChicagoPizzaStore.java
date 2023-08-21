package com.shzhangji.pattern.factory;

public class ChicagoPizzaStore extends PizzaStore {
  @Override
  Pizza createPizza(String type) {
    if ("cheese".equals(type)) {
      return new ChicagoStyleCheesePizza();
    }
    return null;
  }
}
