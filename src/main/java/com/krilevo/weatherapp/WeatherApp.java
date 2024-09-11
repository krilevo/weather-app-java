package com.krilevo.weatherapp;

public class WeatherApp {
  public static void main( String[] args ) {
    try {
      WeatherApiClient client = new WeatherApiClient();
      WeatherData weather = client.getWeatherCoordinates(61.497753, 23.760954); // Tampere, Finland
      System.out.println(weather);
    } catch (Exception e) {
      System.out.println("Error fetching weather data: " + e.getMessage());
    }
  }
}
