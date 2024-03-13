package com.shzhangji.misc;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class EndiannessCheck {
  public static void main(String[] args) {
    var arr = ByteBuffer.allocate(4).order(ByteOrder.nativeOrder()).putInt(1).array();
    System.out.println(arr[0] == 1 ? "Little endian" : "Big endian");
  }
}
