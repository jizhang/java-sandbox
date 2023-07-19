package flink.cdc;

import lombok.Value;
import org.apache.flink.types.RowKind;

import java.util.List;
import java.util.Map;

@Value
public class TargetRow {
  String table;
  List<String> primaryKeys;
  Map<String, String> columns;
  RowKind rowKind;
}
