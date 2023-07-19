package flink.proton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.io.IOException;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.connector.kafka.source.reader.deserializer.KafkaRecordDeserializationSchema;
import org.apache.flink.util.Collector;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class UserActionDeserializer implements KafkaRecordDeserializationSchema<UserAction> {
  private static final ObjectMapper mapper;

  static {
    mapper = new ObjectMapper();
    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
  }

  @Override
  public void deserialize(ConsumerRecord<byte[], byte[]> record, Collector<UserAction> out) throws IOException {
    var action = mapper.readValue(record.value(), UserAction.class);
    action.setPartition(record.partition());
    action.setOffset(record.offset());
    out.collect(action);
  }

  @Override
  public TypeInformation<UserAction> getProducedType() {
    return TypeInformation.of(UserAction.class);
  }
}
