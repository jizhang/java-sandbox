package com.shzhangji.pattern.singleton;

import java.io.Serializable;

public class EagerInit implements Serializable, Cloneable {
  private final static EagerInit INSTANCE = new EagerInit();

  private EagerInit() {}

  public static EagerInit getInstance() {
    return INSTANCE;
  }

  protected Object readResolve() {
    return INSTANCE;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
  }
}
