package flink.proton;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Utils {
  public static String formatTimestampMillis(long timestampMillis, String format) {
    var inst = Instant.ofEpochMilli(timestampMillis);
    var dt = LocalDateTime.ofInstant(inst, ZoneId.systemDefault());
    return DateTimeFormatter.ofPattern(format).format(dt);
  }

  public static String formatTimestamp(double timestampSeconds, String format) {
    return formatTimestampMillis((long) (timestampSeconds * 1000), format);
  }

  public static int toReportDate(long timestampMillis) {
    return Integer.parseInt(formatTimestampMillis(timestampMillis, "yyyyMMdd"));
  }
}
