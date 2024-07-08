package com.shzhangji.flink.distinct;

import java.util.HashMap;
import java.util.Map;
import org.apache.flink.configuration.CheckpointingOptions;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.api.dataview.MapView;
import org.apache.flink.table.functions.AggregateFunction;

public class DistinctTableJob {
  public static void main(String[] args) throws Exception {
    var conf = new Configuration();
    conf.set(CheckpointingOptions.CHECKPOINT_STORAGE, "filesystem");
    conf.set(CheckpointingOptions.CHECKPOINTS_DIRECTORY, "file:///tmp/count-distinct/");

    StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
//    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
    env.enableCheckpointing(5000);
    env.setParallelism(1);

    TableEnvironment tableEnv = StreamTableEnvironment.create(env);
    tableEnv.createTemporarySystemFunction("COUNT_DISTINCT1", CountDistinct1.class);
    tableEnv.createTemporarySystemFunction("COUNT_DISTINCT2", CountDistinct2.class);

    tableEnv.executeSql("CREATE TABLE source_table (f0 STRING) WITH ('connector' = 'datagen', 'rows-per-second' = '10')");
    tableEnv.executeSql("CREATE TABLE print_table (key STRING, cnt BIGINT, cnt1 BIGINT, cnt2 BIGINT) WITH ('connector' = 'print')");
    var sql = "INSERT INTO print_table SELECT SUBSTRING(f0, 0, 1), COUNT(DISTINCT f0), COUNT_DISTINCT1(f0), COUNT_DISTINCT2(f0) FROM source_table GROUP BY SUBSTRING(f0, 0, 1)";
//    System.out.println(tableEnv.explainSql(sql));
    tableEnv.executeSql(sql);
  }

  public static class CountDistinct1 extends AggregateFunction<Long, CountDistinct1.Accumulator> {
    public static class Accumulator {
      public Map<String, Long> values = new HashMap<>();
    }

    @Override
    public Accumulator createAccumulator() {
      return new Accumulator();
    }

    @Override
    public Long getValue(Accumulator accumulator) {
      return (long) accumulator.values.size();
    }

    public void accumulate(Accumulator accumulator, String value) {
      accumulator.values.put(value, 1L);
    }
  }

  public static class CountDistinct2 extends AggregateFunction<Long, CountDistinct2.Accumulator> {
    public static class Accumulator {
      public MapView<String, Long> values = new MapView<>();
      public long count = 0;
    }

    @Override
    public Accumulator createAccumulator() {
      return new Accumulator();
    }

    @Override
    public Long getValue(Accumulator accumulator) {
      return accumulator.count;
    }

    public void accumulate(Accumulator accumulator, String value) throws Exception {
      if (!accumulator.values.contains(value)) {
        accumulator.values.put(value, 1L);
        accumulator.count++;
      }
    }
  }
}
