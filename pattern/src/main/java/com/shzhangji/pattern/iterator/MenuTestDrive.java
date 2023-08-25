package com.shzhangji.pattern.iterator;

public class MenuTestDrive {
  public static void main(String[] args) {
    var pancakeHouseMenu = new PancakeHouseMenu();
    var dinerMenu = new DinerMenu();
    var waitress = new Waitress(pancakeHouseMenu, dinerMenu);
    waitress.printMenu();
  }
}
