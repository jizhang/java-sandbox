package com.shzhangji.pattern.visitor;

public interface Visitor {
  void visitDot(Dot dot);
  void visitCircle(Circle circle);
  void visitRectangle(Rectangle rectangle);
  void visitCompoundGraphic(CompoundShape cg);
}
