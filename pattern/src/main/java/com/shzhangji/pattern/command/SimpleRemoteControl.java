package com.shzhangji.pattern.command;

import lombok.Setter;

public class SimpleRemoteControl {
  @Setter
  Command slot;

  public void buttonWasPressed() {
    slot.execute();
  }
}
