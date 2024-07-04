package com.shzhangji.flink;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

public class DistinctApp {
  public static void main(String[] args) throws Exception {
    var conf = new Configuration();
    StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
    env.enableCheckpointing(5000);
    env.setParallelism(1);
    env.getCheckpointConfig().setCheckpointStorage("file:///tmp/distinct-app/");

    TableEnvironment tableEnv = StreamTableEnvironment.create(env);

    tableEnv.executeSql("CREATE TABLE source_table (f0 STRING) WITH ('connector' = 'datagen', 'rows-per-second' = '1000')");
    tableEnv.executeSql("CREATE TABLE print_table (key STRING, cnt BIGINT) WITH ('connector' = 'print')");
    var sql = "INSERT INTO print_table SELECT SUBSTRING(f0, 0, 1), COUNT(DISTINCT f0) FROM source_table GROUP BY SUBSTRING(f0, 0, 1)";
//    System.out.println(tableEnv.explainSql(sql));
    tableEnv.executeSql(sql);
  }
}
