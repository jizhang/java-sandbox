package com.shzhangji.javasandbox.disruptor;

import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

public class LongEventMain {
  public static void main(String[] args) throws Exception {
    var disruptor = new Disruptor<>(LongEvent::new, 1024, DaemonThreadFactory.INSTANCE);
    disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println(event));
    disruptor.start();

    var ringBuffer = disruptor.getRingBuffer();
    var bb = ByteBuffer.allocate(8);
    for (long l = 0; true; ++l) {
      bb.putLong(0, l);
      ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), bb);
      Thread.sleep(1000);
    }
  }
}
