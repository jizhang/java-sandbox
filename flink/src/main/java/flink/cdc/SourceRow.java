package flink.cdc;

import lombok.Value;
import org.apache.flink.types.RowKind;

import java.util.Map;

@Value
public class SourceRow {
  String table;
  Map<String, String> columns;
  RowKind rowKind;
}
