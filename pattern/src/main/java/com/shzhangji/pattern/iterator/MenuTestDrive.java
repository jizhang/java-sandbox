package com.shzhangji.pattern.iterator;

public class MenuTestDrive {
  public static void main(String[] args) {
    Menu pancakeHouseMenu = new PancakeHouseMenu();
    Menu dinerMenu = new DinerMenu();
    Menu cafeMenu = new CafeMenu();
    var waitress = new Waitress(pancakeHouseMenu, dinerMenu, cafeMenu);
    waitress.printMenu();
  }
}
