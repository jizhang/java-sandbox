package com.shzhangji.pattern.state;

public interface State {
  void insertQuarter();
  void ejectQuarter();
  void turnCrank();
  void dispense();
}
