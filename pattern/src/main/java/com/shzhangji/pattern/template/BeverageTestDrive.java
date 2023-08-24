package com.shzhangji.pattern.template;

public class BeverageTestDrive {
  public static void main(String[] args) {
    System.out.println("Making tea...");
    var tea = new Tea();
    tea.prepareRecipe();

    System.out.println("\nMaking coffee...");
    var coffee = new Coffee();
    coffee.prepareRecipe();
  }
}
