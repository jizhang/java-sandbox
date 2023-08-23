package com.shzhangji.pattern.command;

public class StereoOffCommand implements Command {
  Stereo stereo;
  int previousVolume = 0;

  public StereoOffCommand(Stereo stereo) {
    this.stereo = stereo;
  }

  @Override
  public void execute() {
    stereo.off();
    previousVolume = stereo.getVolume();
  }

  @Override
  public void undo() {
    stereo.on();
    stereo.setVolume(previousVolume);
  }
}
