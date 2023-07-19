package flink.cdc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.types.RowKind;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class TargetRowSinkFunction extends RichSinkFunction<TargetRow> {
  private DataSource dataSource;

  @Override
  public void open(Configuration parameters) throws Exception {
    var config = new HikariConfig();
    config.setJdbcUrl("jdbc:mysql://127.0.0.1:3307/test");
    config.setUsername("root");
    config.setPassword("root");
    dataSource = new HikariDataSource(config);
  }

  @Override
  public void invoke(TargetRow value, Context context) throws Exception {
    if (value.getRowKind() == RowKind.DELETE) {
      executeDelete(value);
    } else {
      executeReplace(value);
    }
  }

  @Override
  public void close() throws Exception {
    dataSource.unwrap(HikariDataSource.class).close();
  }

  private void executeReplace(TargetRow row) throws SQLException {
    var columnNames = List.copyOf(row.getColumns().keySet());

    var columnNamesExpr = columnNames.stream()
      .map(key -> "`" + key + "`")
      .collect(Collectors.joining(", "));

    var valuesExpr = columnNames.stream()
      .map(row.getColumns()::get)
      .map(value -> {
        if (value == null) {
          return "NULL";
        }
        return "'" + escape(value) +  "'";
      })
      .collect(Collectors.joining(", "));

    var sql = String.format("REPLACE INTO `%s` (%s) VALUES (%s)",
      row.getTable(), columnNamesExpr, valuesExpr);

    try (var conn = dataSource.getConnection();
         var stmt = conn.createStatement()) {
      stmt.executeUpdate(sql);
    }
  }

  private void executeDelete(TargetRow row) throws SQLException {
    var where = row.getPrimaryKeys().stream()
      .map(key -> "`" + key + "` = ?")
      .collect(Collectors.joining(" AND "));
    var sql = String.format("DELETE FROM `%s` WHERE %s", row.getTable(), where);

    try (var conn = dataSource.getConnection();
         var stmt = conn.prepareStatement(sql)) {

      for (int i = 0; i < row.getPrimaryKeys().size(); ++i) {
        var value = row.getColumns().get(row.getPrimaryKeys().get(i));
        stmt.setString(i + 1, value);
      }

      stmt.executeUpdate();
    }
  }

  protected String escape(String value) {
    return value.replace("\\", "\\\\")
      .replace("'", "\\'")
      .replace("\r", "\\r")
      .replace("\n", "\\n");
  }
}
