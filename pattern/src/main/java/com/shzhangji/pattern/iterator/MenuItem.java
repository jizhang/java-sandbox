package com.shzhangji.pattern.iterator;

import lombok.Value;

@Value
public class MenuItem {
  String name;
  String description;
  boolean vegetarian;
  double price;
}
