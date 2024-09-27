package com.shzhangji.mapred.kafka;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KafkaLoaderMapperTest {
  @Test
  public void testGetPartitionValue() throws Exception {
    var obj = new KafkaLoaderMapper();
    assertEquals("20240927", obj.getPartitionValue("{\"timestamp\":1727396903}"));
  }
}
