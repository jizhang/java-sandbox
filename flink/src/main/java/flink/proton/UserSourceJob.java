package flink.proton;

import java.util.Properties;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;

public class UserSourceJob {
  public static void main(String[] args) throws Exception {
    var settings = EnvironmentSettings.newInstance().inStreamingMode().build();
    var env = TableEnvironment.create(settings);

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

    env.executeSql("CREATE TABLE ds_user_source ( " +
        "  user_id BIGINT " +
        "  ,source STRING " +
        "  ,medium STRING " +
        "  ,channel STRING " +
        ") WITH ( " +
        "  'connector' = 'jdbc' " +
        "  ,'url' = '" + props.getProperty("jdbc.url") + "' " +
        "  ,'username' = '" + props.getProperty("jdbc.username") + "' " +
        "  ,'password' = '" + props.getProperty("jdbc.password") + "' " +
        "  ,'table-name' = 'ds_user_source' " +
        ")");

    env.executeSql("CREATE TABLE print ( " +
        "  report_minute STRING " +
        "  ,source STRING " +
        "  ,medium STRING " +
        "  ,channel STRING " +
        "  ,user_count BIGINT " +
        ") WITH ('connector' = 'print') ");

    env.executeSql("INSERT INTO print " +
        "SELECT " +
        "  DATE_FORMAT(a.window_start, 'yyyy-MM-dd HH:mm:ss') " +
        "  ,b.source " +
        "  ,b.medium " +
        "  ,MAX(b.channel) " +
        "  ,COUNT(DISTINCT a.user_id) " +
        "FROM TABLE(TUMBLE(TABLE user_action, DESCRIPTOR(time_ltz), INTERVAL '1' MINUTE)) a " +
        "JOIN ds_user_source b ON a.user_id = b.user_id " +
        "GROUP BY a.window_start, b.source, b.medium");
  }
}
