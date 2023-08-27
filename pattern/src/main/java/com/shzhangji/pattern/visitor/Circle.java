package com.shzhangji.pattern.visitor;

import lombok.Getter;

@Getter
public class Circle extends Dot {
  private int radius;

  public Circle(int id, int x, int y, int radius) {
    super(id, x, y);
    this.radius = radius;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitCircle(this);
  }
}
