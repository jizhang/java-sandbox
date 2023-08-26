package com.shzhangji.pattern.state;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SoldState implements State {
  final GumballMachine gumballMachine;

  @Override
  public void insertQuarter() {
    System.out.println("Please wait, we're already giving a gumball");
  }

  @Override
  public void ejectQuarter() {
    System.out.println("Sorry, you already turned the crank");
  }

  @Override
  public void turnCrank() {
    System.out.println("Turning twice doesn't give you another gumball!");
  }

  @Override
  public void dispense() {
    gumballMachine.releaseBall();
    if (gumballMachine.getCount() > 0) {
      gumballMachine.setState(gumballMachine.getNoQuarterState());
    } else {
      System.out.println("Oops, out of gumballs!");
      gumballMachine.setState(gumballMachine.getSoldOutState());
    }
  }
}
