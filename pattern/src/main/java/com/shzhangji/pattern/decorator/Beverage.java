package com.shzhangji.pattern.decorator;

import lombok.Getter;
import lombok.Setter;

public abstract class Beverage {
  String description = "Unknown beverage";
  @Getter @Setter
  Size size = Size.GRANDE;

  public String getDescription() {
    return String.format("%s (%s)", description, size);
  }

  public abstract double cost();
}
