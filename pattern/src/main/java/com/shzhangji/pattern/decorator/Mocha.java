package com.shzhangji.pattern.decorator;

public class Mocha extends CondimentDecorator {
  Beverage beverage;

  public Mocha(Beverage beverage) {
    this.beverage = beverage;
  }

  @Override
  public String getDescription() {
    return beverage.getDescription() + ", Mocha";
  }

  @Override
  public Size getSize() {
    return beverage.getSize();
  }

  @Override
  public double cost() {
    return beverage.cost() + 0.2;
  }
}
