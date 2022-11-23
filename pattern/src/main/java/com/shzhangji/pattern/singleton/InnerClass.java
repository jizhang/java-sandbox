package com.shzhangji.pattern.singleton;

public class InnerClass {
  private static class Holder {
    static final InnerClass INSTANCE = new InnerClass();
  }

  private InnerClass() {}

  public static InnerClass getInstance() {
    return Holder.INSTANCE;
  }
}
