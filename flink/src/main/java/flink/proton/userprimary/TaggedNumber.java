package flink.proton.userprimary;

import lombok.Value;

@Value
public class TaggedNumber {
  String tag;
  Number value;
}
