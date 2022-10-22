package com.shzhangji.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;

public class ZeroCopyServer {
  public static void main(String[] args) throws IOException {
    try (ServerSocketChannel serverSocket = ServerSocketChannel.open()) {
      serverSocket.socket().bind(new InetSocketAddress("localhost", 4444));
      System.out.println("Wait for connection...");
      try (SocketChannel client = serverSocket.accept()){
        System.out.println("Accept " + client.getRemoteAddress());
        try (FileChannel fileChannel = FileChannel.open(Paths.get("/tmp/test.txt"))) {
          fileChannel.transferTo(0, fileChannel.size(), client);
        }
        System.out.println("File sent.");
      }
    }
  }
}
