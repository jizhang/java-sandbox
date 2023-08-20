package com.shzhangji.pattern.observer;

public class WeatherStation {
  public static void main(String[] args) {
    var weatherData = new WeatherData();

    var currentDisplay = new CurrentConditionsDisplay(weatherData);

    weatherData.setMeasurements(80, 65, 30.4f);
    weatherData.setMeasurements(82, 70, 29.2f);
    weatherData.setMeasurements(78, 90, 29.2f);
  }
}
