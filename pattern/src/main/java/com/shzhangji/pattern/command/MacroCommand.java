package com.shzhangji.pattern.command;

public class MacroCommand implements Command {
  Command[] commands;

  public MacroCommand(Command[] commands) {
    this.commands = commands;
  }

  @Override
  public void execute() {
    for (var command : commands) {
      command.execute();
    }
  }

  @Override
  public void undo() {
    for (var command : commands) {
      command.undo();
    }
  }
}
