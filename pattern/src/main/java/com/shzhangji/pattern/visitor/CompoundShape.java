package com.shzhangji.pattern.visitor;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CompoundShape implements Shape {
  private int id;
  private List<Shape> children = new ArrayList<>();

  public CompoundShape(int id) {
    this.id = id;
  }

  @Override
  public void move(int x, int y) {}

  @Override
  public void draw() {}

  @Override
  public void accept(Visitor visitor) {
    visitor.visitCompoundGraphic(this);
  }

  public void add(Shape shape) {
    children.add(shape);
  }
}
