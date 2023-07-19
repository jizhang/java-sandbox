package flink.cdc;

import io.debezium.time.Date;
import io.debezium.time.MicroTime;
import io.debezium.time.Timestamp;
import io.debezium.time.ZonedTimestamp;
import org.apache.kafka.connect.data.Decimal;
import org.apache.kafka.connect.data.Schema;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SourceRowDeserializerTest {
  @Test
  public void testConvertValue() {
    var fn = new SourceRowDeserializer();
    assertNull(fn.convertValue(null, Schema.STRING_SCHEMA));
    assertEquals("test", fn.convertValue("test", Schema.STRING_SCHEMA));

    assertEquals("1", fn.convertValue(1, Schema.INT8_SCHEMA));
    assertEquals("2", fn.convertValue(2, Schema.INT16_SCHEMA));
    assertEquals("3", fn.convertValue(3, Schema.INT32_SCHEMA));
    assertEquals("4", fn.convertValue(4L, Schema.INT64_SCHEMA));
    assertEquals("5.1", fn.convertValue(5.1f, Schema.FLOAT32_SCHEMA));
    assertEquals("6.2", fn.convertValue(6.2, Schema.FLOAT64_SCHEMA));
    assertEquals("10.24", fn.convertValue(new BigDecimal("10.24"), Decimal.schema(2)));

    assertEquals("2021-11-23 20:27:02", fn.convertValue(
      "2021-11-23T12:27:02.001Z", ZonedTimestamp.schema()));
    assertEquals("2021-11-23 12:40:29", fn.convertValue(
      1637671229000L, Timestamp.schema()));
    assertEquals("2021-11-23", fn.convertValue(
      18954, Date.schema()));
    assertEquals("13:21:30", fn.convertValue(
      48090_000_000L, MicroTime.schema()));
  }
}
