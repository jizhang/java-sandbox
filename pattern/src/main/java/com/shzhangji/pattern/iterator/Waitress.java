package com.shzhangji.pattern.iterator;

public class Waitress {
  PancakeHouseMenu pancakeHouseMenu;
  DinerMenu dinerMenu;

  public Waitress(PancakeHouseMenu pancakeHouseMenu, DinerMenu dinerMenu) {
    this.pancakeHouseMenu = pancakeHouseMenu;
    this.dinerMenu = dinerMenu;
  }

  public void printMenu() {
    var pancakeIterator = pancakeHouseMenu.createIterator();
    var dinerIterator = dinerMenu.createIterator();

    System.out.println("MENU\n----\nBREAKFAST");
    printMenu(pancakeIterator);

    System.out.println("\nLUNCH");
    printMenu(dinerIterator);
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
