package com.shzhangji.pattern.iterator;

import java.util.LinkedHashMap;
import java.util.Map;

public class CafeMenu implements Menu {
  Map<String, MenuItem> menuItems =  new LinkedHashMap<>();

  public CafeMenu() {
    addItem("Veggie Burger and Air Fries",
        "Veggie burger on a whole wheat bun, lettuce, tomato, and fries",
        true, 3.99);
    addItem("Soup of the day",
        "A cup of the soup of the day, with a side salad",
        false, 3.69);
    addItem("Burrito",
        "A large burrito, with whole pinto beans, salsa, guacamole",
        true, 4.19);
  }

  public void addItem(String name, String description, boolean vegeterian, double price) {
    menuItems.put(name, new MenuItem(name, description, vegeterian, price));
  }

  @Override
  public Iterator<MenuItem> createIterator() {
    return new IteratorAdapter<>(menuItems.values().iterator());
  }
}
