package com.shzhangji.pattern.decorator;

public abstract class CondimentDecorator extends Beverage {
  public abstract String getDescription();
  public abstract Size getSize();

  @Override
  public void setSize(Size size) {
    throw new UnsupportedOperationException("Unable to set size for condiment.");
  }
}
