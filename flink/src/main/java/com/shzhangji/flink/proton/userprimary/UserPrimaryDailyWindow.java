package com.shzhangji.flink.proton.userprimary;

import com.shzhangji.flink.proton.Utils;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class UserPrimaryDailyWindow implements AllWindowFunction<TaggedNumber, UserPrimaryDaily, TimeWindow> {
  @Override
  public void apply(TimeWindow window, Iterable<TaggedNumber> values, Collector<UserPrimaryDaily> out) {
    var data = new UserPrimaryDaily();
    data.setReportDate(Utils.toReportDate(window.getStart()));
    for (var measure : values) {
      switch (measure.getTag()) {
        case "user_count":
          data.setUserCount(measure.getValue().longValue());
          break;
        case "single_page_user_count":
          data.setSinglePageUserCount(measure.getValue().longValue());
          break;
        case "session_count":
          data.setSessionCount(measure.getValue().longValue());
          break;
        case "total_session_seconds":
          data.setTotalSessionSeconds(measure.getValue().longValue());
          break;
      }
    }
    out.collect(data);
  }
}
