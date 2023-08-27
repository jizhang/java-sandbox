package com.shzhangji.pattern.visitor;

import lombok.Getter;

@Getter
public class Dot implements Shape {
  private int id;
  private int x;
  private int y;

  public Dot(int id, int x, int y) {
    this.id = id;
    this.x = x;
    this.y = y;
  }

  @Override
  public void move(int x, int y) {}

  @Override
  public void draw() {}

  @Override
  public void accept(Visitor visitor) {
    visitor.visitDot(this);
  }
}
