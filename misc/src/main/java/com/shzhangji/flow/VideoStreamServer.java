package com.shzhangji.flow;

import java.util.concurrent.Executor;
import java.util.concurrent.SubmissionPublisher;

public class VideoStreamServer extends SubmissionPublisher<VideoFrame> {
  public VideoStreamServer(Executor executor, int maxBufferCapacity) {
    super(executor, maxBufferCapacity);
  }
}
