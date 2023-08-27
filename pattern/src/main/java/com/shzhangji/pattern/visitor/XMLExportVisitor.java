package com.shzhangji.pattern.visitor;

public class XMLExportVisitor implements Visitor {
  private int indentLevel;
  private StringBuilder sb;

  public String export(Shape... shapes) {
    indentLevel = 0;
    sb = new StringBuilder();
    sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "\n");
    for (var shape : shapes) {
      shape.accept(this);
    }
    return sb.toString();
  }

  @Override
  public void visitDot(Dot d) {
    var str = "<dot>" + "\n" +
        "    <id>" + d.getId() + "</id>" + "\n" +
        "    <x>" + d.getX() + "</x>" + "\n" +
        "    <y>" + d.getY() + "</y>" + "\n" +
        "</dot>";
    sb.append(applyIndent(str));
  }

  @Override
  public void visitCircle(Circle c) {
    var str = "<circle>" + "\n" +
        "    <id>" + c.getId() + "</id>" + "\n" +
        "    <x>" + c.getX() + "</x>" + "\n" +
        "    <y>" + c.getY() + "</y>" + "\n" +
        "    <radius>" + c.getRadius() + "</radius>" + "\n" +
        "</circle>";
    sb.append(applyIndent(str));
  }

  @Override
  public void visitRectangle(Rectangle r) {
    var str = "<rectangle>" + "\n" +
        "    <id>" + r.getId() + "</id>" + "\n" +
        "    <x>" + r.getX() + "</x>" + "\n" +
        "    <y>" + r.getY() + "</y>" + "\n" +
        "    <width>" + r.getWidth() + "</width>" + "\n" +
        "    <height>" + r.getHeight() + "</height>" + "\n" +
        "</rectangle>";
    sb.append(applyIndent(str));
  }

  @Override
  public void visitCompoundGraphic(CompoundShape cg) {
    var str = "<compound_graphic>" + "\n" +
        "    <id>" + cg.getId() + "</id>";
    sb.append(applyIndent(str));

    ++indentLevel;
    for (var shape : cg.getChildren()) {
      shape.accept(this);
    }
    --indentLevel;

    sb.append(applyIndent("</compound_graphic>"));
  }

  private String applyIndent(String str) {
    var indent = " ".repeat(indentLevel * 4);
    return indent + str.replace("\n", "\n" + indent) + "\n";
  }
}
