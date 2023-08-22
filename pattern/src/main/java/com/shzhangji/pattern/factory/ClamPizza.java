package com.shzhangji.pattern.factory;

import com.shzhangji.pattern.factory.ingredient.PizzaIngredientFactory;

public class ClamPizza extends Pizza {
  PizzaIngredientFactory ingredientFactory;

  public ClamPizza(PizzaIngredientFactory ingredientFactory) {
    this.ingredientFactory = ingredientFactory;
  }

  @Override
  void prepare() {
    System.out.println("Preparing " + name);
    dough = ingredientFactory.createDough();
    sauce = ingredientFactory.createSauce();
    cheese = ingredientFactory.createCheese();
    clam = ingredientFactory.createClam();
  }
}
