package com.shzhangji.pattern.command;

public class SimpleRemoteControlTest {
  public static void main(String[] args) {
    var remote = new SimpleRemoteControl();

    var light = new Light("Living Room");
    var lightOn = new LightOnCommand(light);

    var garageDoor = new GarageDoor();
    var garageOpen = new GarageDoorOpenCommand(garageDoor);

    remote.setSlot(lightOn);
    remote.buttonWasPressed();
    remote.setSlot(garageOpen);
    remote.buttonWasPressed();
  }
}
