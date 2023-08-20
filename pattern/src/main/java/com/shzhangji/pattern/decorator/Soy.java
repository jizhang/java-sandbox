package com.shzhangji.pattern.decorator;

public class Soy extends CondimentDecorator {
  Beverage beverage;

  public Soy(Beverage beverage) {
    this.beverage = beverage;
  }

  @Override
  public String getDescription() {
    return beverage.getDescription() + ", Soy";
  }

  @Override
  public Size getSize() {
    return beverage.getSize();
  }

  @Override
  public double cost() {
    double cost = beverage.cost();
    switch (getSize()) {
      case TALL:
        cost += 0.1;
        break;
      case GRANDE:
        cost += 0.15;
        break;
      case VENTI:
        cost += 0.2;
        break;
    }
    return cost;
  }
}
