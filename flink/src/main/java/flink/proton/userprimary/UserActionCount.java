package flink.proton.userprimary;

import flink.proton.UserAction;
import lombok.Data;

@Data
public class UserActionCount {
  private long userId;
  private long actionCount;

  public UserActionCount(UserAction action) {
    userId = action.getUserId();
    actionCount = 1;
  }

  public UserActionCount merge(UserActionCount that) {
    actionCount += that.actionCount;
    return this;
  }
}
