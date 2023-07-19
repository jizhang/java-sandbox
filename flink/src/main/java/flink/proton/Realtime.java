package flink.proton;

import java.util.Properties;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;

public class Realtime {
  public static void main(String[] args) throws Exception {
    var settings = EnvironmentSettings.newInstance().inStreamingMode().build();
    var env = TableEnvironment.create(settings);
    var stmtSet = env.createStatementSet();

    var props = new Properties();
    props.setProperty("jdbc.url", "jdbc:mysql://localhost:3306/proton");
    props.setProperty("jdbc.username", "root");
    props.setProperty("jdbc.password", "");

    env.executeSql("CREATE TABLE user_action ( " +
      "  `partition` INT METADATA VIRTUAL " +
      "  ,`offset` BIGINT METADATA VIRTUAL " +
      "  ,`user_id` BIGINT " +
      "  ,`action_id` INT " +
      "  ,`timestamp` DOUBLE " +
      "  ,`time_ltz` AS TO_TIMESTAMP_LTZ(`timestamp` * 1000, 3) " +
      "  ,WATERMARK FOR `time_ltz` AS `time_ltz` - INTERVAL '5' SECOND " +
      ") WITH ( " +
      "  'connector' = 'kafka' " +
      "  ,'topic' = 'flink_test' " +
      "  ,'properties.bootstrap.servers' = 'localhost:9092' " +
      "  ,'properties.group.id' = 'flink_group' " +
      "  ,'scan.startup.mode' = 'latest-offset' " +
      "  ,'format' = 'json' " +
      ") ");

    env.executeSql("CREATE TABLE ds_user_action ( " +
      "  `partition` INT " +
      "  ,`offset` BIGINT " +
      "  ,`user_id` BIGINT " +
      "  ,`action_id` INT " +
      "  ,`timestamp` DOUBLE " +
      "  ,`created_at` STRING " +
      "  ,PRIMARY KEY (`partition`, `offset`) NOT ENFORCED " +
      ") WITH ( " +
      "  'connector' = 'jdbc' " +
      "  ,'url' = '" + props.getProperty("jdbc.url") + "' " +
      "  ,'username' = '" + props.getProperty("jdbc.username") + "' " +
      "  ,'password' = '" + props.getProperty("jdbc.password") + "' " +
      "  ,'table-name' = 'ds_user_action' " +
      ")");

    stmtSet.addInsertSql("INSERT INTO ds_user_action " +
      "SELECT " +
      "  `partition` " +
      "  ,`offset` " +
      "  ,`user_id` " +
      "  ,`action_id` " +
      "  ,`timestamp` " +
      "  ,DATE_FORMAT(`time_ltz`, 'yyyy-MM-dd HH:mm:ss') " +
      "FROM user_action");

    env.executeSql("CREATE TABLE da_user_count_1min ( " +
      "  report_minute STRING " +
      "  ,user_count BIGINT " +
      "  ,PRIMARY KEY (report_minute) NOT ENFORCED " +
      ") WITH ( " +
      "  'connector' = 'jdbc' " +
      "  ,'url' = '" + props.getProperty("jdbc.url") + "' " +
      "  ,'username' = '" + props.getProperty("jdbc.username") + "' " +
      "  ,'password' = '" + props.getProperty("jdbc.password") + "' " +
      "  ,'table-name' = 'da_user_count_1min' " +
      ")");

    stmtSet.addInsertSql("INSERT INTO da_user_count_1min " +
      "SELECT DATE_FORMAT(window_start, 'yyyy-MM-dd HH:mm:ss'), COUNT(DISTINCT user_id) " +
      "FROM TABLE(TUMBLE(TABLE user_action, DESCRIPTOR(time_ltz), INTERVAL '1' MINUTE)) " +
      "WHERE action_id IN (8, 9) " +
      "GROUP BY window_start ");

    env.executeSql("CREATE TABLE da_user_count_5min ( " +
      "  report_minute STRING " +
      "  ,user_count BIGINT " +
      "  ,PRIMARY KEY (report_minute) NOT ENFORCED " +
      ") WITH ( " +
      "  'connector' = 'jdbc' " +
      "  ,'url' = '" + props.getProperty("jdbc.url") + "' " +
      "  ,'username' = '" + props.getProperty("jdbc.username") + "' " +
      "  ,'password' = '" + props.getProperty("jdbc.password") + "' " +
      "  ,'table-name' = 'da_user_count_5min' " +
      ")");

    stmtSet.addInsertSql("INSERT INTO da_user_count_5min " +
      "SELECT DATE_FORMAT(window_time, 'yyyy-MM-dd HH:mm:00'), COUNT(DISTINCT user_id) " +
      "FROM TABLE(HOP(TABLE user_action, DESCRIPTOR(time_ltz), INTERVAL '1' MINUTE, INTERVAL '5' MINUTE)) " +
      "WHERE action_id IN (8, 9) " +
      "GROUP BY window_time ");

    stmtSet.execute();
  }
}
