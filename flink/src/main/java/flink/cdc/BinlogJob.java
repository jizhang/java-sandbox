package flink.cdc;

import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;

public class BinlogJob {
  public static void main(String[] args) throws Exception {
    var conf = new Configuration();
    var env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
    // var env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
    env.enableCheckpointing(5000);
    env.setParallelism(1);

    var source = MySqlSource.<SourceRow>builder()
      .hostname("127.0.0.1")
      .port(3307)
      .username("root")
      .password("root")
      .databaseList("test")
      .tableList("test.zj_tmp1")
      .deserializer(new SourceRowDeserializer())
      .startupOptions(StartupOptions.latest())
      .build();

    var sourceRows = env.fromSource(source, WatermarkStrategy.noWatermarks(), "mysql")
      .setParallelism(1)
      .keyBy(SourceRow::getTable);

    sourceRows.map(row -> {
      var primaryKeys = new ArrayList<String>();
      primaryKeys.add("id");

      return new TargetRow(
        "zj_tmp2",
        primaryKeys,
        row.getColumns(),
        row.getRowKind());
    }).addSink(new TargetRowSinkFunction());

    env.execute("BinlogJob");
  }
}
