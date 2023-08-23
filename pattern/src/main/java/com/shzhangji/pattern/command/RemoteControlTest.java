package com.shzhangji.pattern.command;

public class RemoteControlTest {
  public static void main(String[] args) {
    var remote = new RemoteControl();

    var livingRoomLight = new Light("Living Room");
    var kitchenLight = new Light("Kitchen");
    var stereo = new Stereo("Living Room");

    remote.setCommand(0, new LightOnCommand(livingRoomLight), new LightOffCommand(livingRoomLight));
    remote.setCommand(1, new LightOnCommand(kitchenLight), new LightOffCommand(kitchenLight));
    remote.setCommand(3, new StereoOnWithCDCommand(stereo), new StereoOffCommand(stereo));
    System.out.println(remote);

    remote.onButtonWasPressed(0);
    remote.offButtonWasPressed(0);
    remote.onButtonWasPressed(1);
    remote.offButtonWasPressed(1);
    remote.onButtonWasPressed(3);
    remote.offButtonWasPressed(3);
    System.out.println(remote);

    remote.undoButtonWasPressed();
  }
}
