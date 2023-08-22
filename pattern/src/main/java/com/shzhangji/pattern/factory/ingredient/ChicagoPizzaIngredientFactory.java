package com.shzhangji.pattern.factory.ingredient;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
  @Override
  public Dough createDough() {
    return new ThickCrustDough();
  }

  @Override
  public Sauce createSauce() {
    return new PlumTomatoSauce();
  }

  @Override
  public Cheese createCheese() {
    return new Mozzarella();
  }

  @Override
  public Veggies[] createVeggies() {
    return new Veggies[] { new BlackOlives(), new Spinach(), new EggPlant() };
  }

  @Override
  public Pepperoni createPepperoni() {
    return new SlicedPepperoni();
  }

  @Override
  public Clams createClam() {
    return new FrozenClams();
  }
}
