package com.shzhangji.pattern.state;

import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class HasQuarterState implements State {
  final GumballMachine gumballMachine;
  Random randomWinner = new Random();

  @Override
  public void insertQuarter() {
    System.out.println("You can't insert another quarter");
  }

  @Override
  public void ejectQuarter() {
    System.out.println("Quarter returned");
    gumballMachine.setState(gumballMachine.getNoQuarterState());
  }

  @Override
  public void turnCrank() {
    System.out.println("You turned...");
    int winner = randomWinner.nextInt(10);
    if (winner == 0 && gumballMachine.getCount() > 1) {
      gumballMachine.setState(gumballMachine.getWinnerState());
    } else {
      gumballMachine.setState(gumballMachine.getSoldState());
    }
  }

  @Override
  public void dispense() {
    System.out.println("No gumball dispensed");
  }
}
