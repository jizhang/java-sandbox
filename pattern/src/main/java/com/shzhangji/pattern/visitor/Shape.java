package com.shzhangji.pattern.visitor;

public interface Shape {
  void move(int x, int y);
  void draw();
  void accept(Visitor visitor);
}
