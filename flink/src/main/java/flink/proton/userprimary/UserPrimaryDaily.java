package flink.proton.userprimary;

import lombok.Data;

@Data
public class UserPrimaryDaily {
  private int reportDate;
  private long userCount;
  private long singlePageUserCount;
  private long sessionCount;
  private long totalSessionSeconds;
}
