package com.shzhangji.pattern.command;

public class RemoteControl {
  Command[] onCommands;
  Command[] offCommands;

  public RemoteControl() {
    onCommands = new Command[7];
    offCommands = new Command[7];

    var noCommand = new NoCommand();
    for (int i = 0; i < 7; ++i) {
      onCommands[i] = noCommand;
      offCommands[i] = noCommand;
    }
  }

  public void setCommand(int slot, Command onCommand, Command offCommand) {
    onCommands[slot] = onCommand;
    offCommands[slot] = offCommand;
  }

  public void onButtonWasPressed(int slot) {
    onCommands[slot].execute();
  }

  public void offButtonWasPressed(int slot) {
    offCommands[slot].execute();
  }

  @Override
  public String toString() {
    var sb = new StringBuilder("\n------ Remote Control ------\n");
    for (int i = 0; i < 7; ++i) {
      sb.append("[slot ").append(i).append("] ").append(onCommands[i].getClass().getName());
      sb.append("    ").append(offCommands[i].getClass().getName()).append("\n");
    }
    return sb.toString();
  }
}
