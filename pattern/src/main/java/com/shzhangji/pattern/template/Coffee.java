package com.shzhangji.pattern.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Coffee extends CaffeineBeverage {
  @Override
  void brew() {
    System.out.println("Dripping coffe through filter");
  }

  @Override
  void addCondiments() {
    System.out.println("Adding Sugar and Milk");
  }

  @Override
  boolean customerWantsCondiments() {
    return getUserInput().toLowerCase().startsWith("y");
  }

  private String getUserInput() {
    System.out.println("Would you like milk and sugar with your coffee? (y/n)");
    var reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      return reader.readLine();
    } catch (IOException e) {
      throw new RuntimeException("IO error trying to read your answer", e);
    }
  }
}
