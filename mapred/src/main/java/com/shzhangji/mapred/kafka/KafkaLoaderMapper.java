package com.shzhangji.mapred.kafka;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaLoaderMapper extends Mapper<NullWritable, Text, NullWritable, Text>  {
  private static final Logger log = LoggerFactory.getLogger(KafkaLoaderMapper.class);

  private MultipleOutputs<NullWritable, Text> mos;
  private ObjectMapper objectMapper = new ObjectMapper();
  private KafkaInputSplit split;

  @Override
  protected void setup(Mapper<NullWritable, Text, NullWritable, Text>.Context context)
      throws IOException, InterruptedException {

    mos = new MultipleOutputs<>(context);
    split = (KafkaInputSplit) context.getInputSplit();
  }

  @Override
  protected void map(NullWritable key, Text value,
      Mapper<NullWritable, Text, NullWritable, Text>.Context context)
      throws IOException, InterruptedException {

    String partitionValue;
    try {
      partitionValue = getPartitionValue(value.toString());
    } catch (Exception e) {
      log.warn("Fail to get partition value - {}", e.getMessage());
      return;
    }

    String baseOutputPath = String.format("%s=%s/%s-%d-%d",
        KafkaLoader.PARTITION_COLUMN, partitionValue,
        split.getTopic(), split.getPartition(), split.getFromOffset());
    mos.write(key, value, baseOutputPath);
  }

  @Override
  protected void cleanup(Mapper<NullWritable, Text, NullWritable, Text>.Context context)
      throws IOException, InterruptedException {

    mos.close();
  }

  protected String getPartitionValue(String value) throws IOException {
    JsonNode node = objectMapper.readTree(value);
    long timestamp = node.path(KafkaLoader.TIMESTAMP_KEY).longValue();
    Date date = new Date(timestamp * 1000);
    DateFormat df = new SimpleDateFormat("yyyyMMdd");
    return df.format(date);
  }
}
