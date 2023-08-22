package com.shzhangji.pattern.factory;

import com.shzhangji.pattern.factory.ingredient.ChicagoPizzaIngredientFactory;
import com.shzhangji.pattern.factory.ingredient.PizzaIngredientFactory;

public class ChicagoPizzaStore extends PizzaStore {
  @Override
  Pizza createPizza(String type) {
    Pizza pizza = null;
    PizzaIngredientFactory ingredientFactory = new ChicagoPizzaIngredientFactory();

    if ("cheese".equals(type)) {
      pizza = new CheesePizza(ingredientFactory);
      pizza.setName("Chicago Style Cheese Pizza");
    }

    if ("clam".equals(type)) {
      pizza = new ClamPizza(ingredientFactory);
      pizza.setName("Chicago Style Clam Pizza");
    }

    return pizza;
  }
}
