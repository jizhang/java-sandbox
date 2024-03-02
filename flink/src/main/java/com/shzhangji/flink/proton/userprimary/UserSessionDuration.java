package com.shzhangji.flink.proton.userprimary;

import com.shzhangji.flink.proton.UserAction;
import lombok.Data;

@Data
public class UserSessionDuration {
  private long userId;
  private long start;
  private long end;

  public UserSessionDuration(UserAction action) {
    userId = action.getUserId();
    start = end = (long) (action.getTimestamp() * 1000);
  }

  public UserSessionDuration merge(UserSessionDuration that) {
    start = Math.min(start, that.start);
    end = Math.max(end, that.end);
    return this;
  }
}
