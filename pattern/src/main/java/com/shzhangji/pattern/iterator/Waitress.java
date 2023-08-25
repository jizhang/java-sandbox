package com.shzhangji.pattern.iterator;

public class Waitress {
  Menu pancakeHouseMenu;
  Menu dinerMenu;
  Menu cafeMenu;

  public Waitress(Menu pancakeHouseMenu, Menu dinerMenu, Menu cafeMenu) {
    this.pancakeHouseMenu = pancakeHouseMenu;
    this.dinerMenu = dinerMenu;
    this.cafeMenu = cafeMenu;
  }

  public void printMenu() {
    var pancakeIterator = pancakeHouseMenu.createIterator();
    var dinerIterator = dinerMenu.createIterator();
    var cafeIterator = cafeMenu.createIterator();

    System.out.println("MENU\n----\nBREAKFAST");
    printMenu(pancakeIterator);

    System.out.println("\nLUNCH");
    printMenu(dinerIterator);

    System.out.println("\nDINNER");
    printMenu(cafeIterator);
  }

  private void printMenu(Iterator<MenuItem> iterator) {
    while (iterator.hastNext()) {
      var item = iterator.next();
      System.out.print(item.getName() + ", ");
      System.out.print(item.getPrice() + " -- ");
      System.out.println(item.getDescription());
    }
  }
}
