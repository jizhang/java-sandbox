package com.shzhangji.pattern.command;

public interface Command {
  void execute();
  void undo();
}
