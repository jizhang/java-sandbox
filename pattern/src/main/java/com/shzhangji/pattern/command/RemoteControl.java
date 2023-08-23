package com.shzhangji.pattern.command;

public class RemoteControl {
  Command[] onCommands;
  Command[] offCommands;
  Command undoCommand;

  public RemoteControl() {
    onCommands = new Command[7];
    offCommands = new Command[7];

    var noCommand = new NoCommand();
    for (int i = 0; i < 7; ++i) {
      onCommands[i] = noCommand;
      offCommands[i] = noCommand;
    }
    undoCommand = noCommand;
  }

  public void setCommand(int slot, Command onCommand, Command offCommand) {
    onCommands[slot] = onCommand;
    offCommands[slot] = offCommand;
  }

  public void onButtonWasPressed(int slot) {
    onCommands[slot].execute();
    undoCommand = onCommands[slot];
  }

  public void offButtonWasPressed(int slot) {
    offCommands[slot].execute();
    undoCommand = offCommands[slot];
  }

  public void undoButtonWasPressed() {
    undoCommand.undo();
  }

  @Override
  public String toString() {
    var sb = new StringBuilder("\n------ Remote Control ------\n");
    for (int i = 0; i < 7; ++i) {
      sb.append("[slot ").append(i).append("] ").append(onCommands[i].getClass().getName());
      sb.append("    ").append(offCommands[i].getClass().getName()).append("\n");
    }
    sb.append("[undo] ").append(undoCommand.getClass().getName()).append("\n");
    return sb.toString();
  }
}
