package com.shzhangji.pattern.factory;

import com.shzhangji.pattern.factory.ingredient.NYPizzaIngredientFactory;
import com.shzhangji.pattern.factory.ingredient.PizzaIngredientFactory;

public class NYPizzaStore extends PizzaStore {
  @Override
  Pizza createPizza(String type) {
    Pizza pizza = null;
    PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

    if ("cheese".equals(type)) {
      pizza = new CheesePizza(ingredientFactory);
      pizza.setName("New York Style Cheese Pizza");
    }

    if ("clam".equals(type)) {
      pizza = new ClamPizza(ingredientFactory);
      pizza.setName("New York Style Clam Pizza");
    }

    return pizza;
  }
}
