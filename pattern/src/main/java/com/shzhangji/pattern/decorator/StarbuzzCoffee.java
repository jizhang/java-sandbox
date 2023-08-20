package com.shzhangji.pattern.decorator;

public class StarbuzzCoffee {
  public static void main(String[] args) {
    Beverage beverage = new Espresso();
    System.out.println(beverage.getDescription() + " $" + beverage.cost());

    Beverage beverage3 = new HouseBlend();
    beverage3 = new Mocha(beverage3);
    System.out.println(beverage3.getDescription() + " $" + beverage3.cost());
  }
}
