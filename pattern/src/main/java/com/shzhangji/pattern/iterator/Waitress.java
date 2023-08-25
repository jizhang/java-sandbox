package com.shzhangji.pattern.iterator;

public class Waitress {
  MenuComponent allMenus;

  public Waitress(MenuComponent allMenus) {
    this.allMenus = allMenus;
  }

  public void printMenu() {
    allMenus.print();
  }

  public void printVegetarianMenu() {
    var iterator = new CompositeIterator(allMenus.createIterator());
    System.out.println("\nVEGETARIAN MENU\n----");
    while (iterator.hasNext()) {
      var item = iterator.next();
      try {
        if (item.isVegetarian()) {
          item.print();
        }
      } catch (UnsupportedOperationException e) {}
    }
  }
}
