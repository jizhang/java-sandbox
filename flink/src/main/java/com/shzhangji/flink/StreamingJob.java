package com.shzhangji.flink;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.connector.file.src.reader.TextLineInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class StreamingJob {
  public static void main(String[] args) throws Exception {
    final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

    var source = FileSource.forRecordStreamFormat(new TextLineInputFormat(), new Path("flink/data/wordcount.txt")).build();
    var ds = env.fromSource(source, WatermarkStrategy.noWatermarks(), "file-source");
    ds.flatMap(new Tokenizer()).keyBy(t -> t.f0).sum(1).print();

    env.execute("Flink Streaming Java API Skeleton");
  }

  public static class Tokenizer implements FlatMapFunction<String, Tuple2<String, Integer>> {
    @Override
    public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
      for (String word : value.split("\\s+")) {
        out.collect(Tuple2.of(word, 1));
      }
    }
  }
}
