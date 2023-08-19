package com.shzhangji.pattern.strategy;

import lombok.Setter;

public abstract class Duck {
  @Setter
  QuackBehavior quackBehavior;
  @Setter
  FlyBehavior flyBehavior;

  public void performQuack() {
    quackBehavior.quack();
  }

  public void performFly() {
    flyBehavior.fly();
  }

  public abstract void display();

  public void swim() {
    System.out.println("All ducks float, even decoys!");
  }
}
