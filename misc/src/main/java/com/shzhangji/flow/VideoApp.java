package com.shzhangji.flow;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

// https://www.baeldung.com/rxjava-vs-java-flow-api
public class VideoApp {
  public static void main(String[] args) throws Exception {
    var serverExecutor = Executors.newSingleThreadExecutor();
    var streamServer = new VideoStreamServer(serverExecutor, 5);
    streamServer.subscribe(new VideoPlayer());

    var executor = Executors.newScheduledThreadPool(1);
    var frameNumber = new AtomicLong();
    executor.scheduleWithFixedDelay(() -> {
      streamServer.offer(new VideoFrame(frameNumber.getAndIncrement()), (subscriber, videoFrame) -> {
        subscriber.onError(new RuntimeException("Frame #" + videoFrame.getNumber() + " is dropped because of backpressure."));
        return true;
      });
    }, 0, 1, TimeUnit.MILLISECONDS);

    TimeUnit.SECONDS.sleep(1);

    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.SECONDS);

    streamServer.close();
    serverExecutor.shutdown();
  }
}
