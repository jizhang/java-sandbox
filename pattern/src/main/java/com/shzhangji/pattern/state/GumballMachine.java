package com.shzhangji.pattern.state;

import lombok.Getter;
import lombok.Setter;

public class GumballMachine {
  @Getter
  State soldOutState;
  @Getter
  State noQuarterState;
  @Getter
  State hasQuarterState;
  @Getter
  State soldState;
  @Getter
  State winnerState;
  @Setter
  State state;
  @Getter
  int count;

  public GumballMachine(int numberGumballs) {
    soldOutState = new SoldOutState(this);
    noQuarterState = new NoQuarterState(this);
    hasQuarterState = new HasQuarterState(this);
    soldState = new SoldState(this);
    winnerState = new WinnerState(this);

    count = numberGumballs;
    state = count > 0 ? noQuarterState : soldOutState;
  }

  public void insertQuarter() {
    state.insertQuarter();
  }

  public void ejectQuarter() {
    state.ejectQuarter();
  }

  public void turnCrank() {
    state.turnCrank();
    state.dispense();
  }

  public void releaseBall() {
    System.out.println("A gumball comes rolling out the slot...");
    if (count > 0) --count;
  }

  @Override
  public String toString() {
    return "\nMighty Gumball, Inc.\n" +
        "Java-enabled Standing Gumball Model #2004\n" +
        "Inventory: " + count + " gumballs\n" +
        "Machine is waiting for quarter\n";
  }
}
