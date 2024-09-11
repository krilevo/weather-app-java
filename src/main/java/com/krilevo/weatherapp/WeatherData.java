package com.krilevo.weatherapp;

// Class to store weather data
public class WeatherData {
  private double temperature; // Temperature in Celsius
  private double windspeed; // Windspeed in kilometers per hour

  // Constructor to initialize the WeatherData object with temperature and windspeed
  public WeatherData(double temperature, double windspeed) {
    this.temperature = temperature;
    this.windspeed = windspeed;
  }

  // Getter method for temperature
  public double getTemperature() {
    return temperature;
  }

  // Getter method for windspeed
  public double getWindspeed() {
    return windspeed;
  }

  // Override the default 'toString()' method to return class attributes
  @Override
  public String toString() {
    return "Temperature: " + temperature + "°C\n" + "Windspeed: " + windspeed + " km/h\n";
  }
}
