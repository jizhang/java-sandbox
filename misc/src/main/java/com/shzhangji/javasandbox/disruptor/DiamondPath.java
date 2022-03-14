package com.shzhangji.javasandbox.disruptor;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.BasicExecutor;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

public class DiamondPath {
  public static void main(String[] args) throws Exception {
    // runWithDSL();
    runWithAPI();
  }

  static void runWithDSL() throws Exception {
    var disruptor = new Disruptor<>(FizzBuzzEvent::new, 1024, DaemonThreadFactory.INSTANCE);

    EventHandler<FizzBuzzEvent> fizzHandler = (event, sequence, endOfBath) -> {
      event.setFizz(10000 + event.getValue());
    };

    EventHandler<FizzBuzzEvent> buzzHandler = (event, sequence, endOfBatch) -> {
      event.setBuzz(20000 + event.getValue());
    };

    disruptor.handleEventsWith(fizzHandler, buzzHandler)
      .then((event, sequence, endOfBatch) -> System.out.println(event));

    disruptor.start();

    publish(disruptor.getRingBuffer());
  }

  static void runWithAPI() throws Exception {
    var ringBuffer = RingBuffer.createSingleProducer(FizzBuzzEvent::new, 1024);
    var firstBarrier = ringBuffer.newBarrier();

    var fizzProcessor = new BatchEventProcessor<>(ringBuffer, firstBarrier, (event, sequence, endOfBatch) -> {
      event.setFizz(10000 + event.getValue());
    });

    var buzzProcessor = new BatchEventProcessor<>(ringBuffer, firstBarrier, (event, sequence, endOfBatch) -> {
      event.setBuzz(20000 + event.getValue());
    });

    var secondBarrier = ringBuffer.newBarrier(fizzProcessor.getSequence(), buzzProcessor.getSequence());
    var printProcessor = new BatchEventProcessor<>(ringBuffer, secondBarrier, (event, sequence, endOfBatch) -> {
      System.out.println(event);
    });

    var executor = new BasicExecutor(DaemonThreadFactory.INSTANCE);
    executor.execute(fizzProcessor);
    executor.execute(buzzProcessor);
    executor.execute(printProcessor);

    publish(ringBuffer);
  }

  static void publish(RingBuffer<FizzBuzzEvent> ringBuffer) throws Exception {
    var bb = ByteBuffer.allocate(8);
    for (long l = 0; true; ++l) {
      bb.putLong(0, l);
      ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(buffer.getLong(0)), bb);
      Thread.sleep(1000);
    }
  }
}
