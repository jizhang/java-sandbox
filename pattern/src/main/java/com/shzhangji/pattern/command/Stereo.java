package com.shzhangji.pattern.command;

import lombok.Getter;

public class Stereo {
  String name;
  @Getter
  int volume = 10;

  public Stereo(String name) {
    this.name = name;
  }

  public void on() {
    System.out.println(name + " stereo is on");
  }

  public void off() {
    System.out.println(name + " stereo is off");
  }

  public void setCD() {
    System.out.println(name + " stereo is set for CD input");
  }

  public void setVolume(int volume) {
    this.volume = volume;
    System.out.println(name + " stereo volume set to " + volume);
  }
}
