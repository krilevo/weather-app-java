package com.krilevo.weatherapp;

import java.net.URI; // Imports the URI class for handling Uniform Resource Identifiers (URIs), used to create and manipulate URL strings
import java.net.http.HttpClient; // Imports the HttpClient class for sending HTTP requests and receiving HTTP responses
import java.net.http.HttpRequest; // Imports the HttpRequest class to build and send HTTP requests
import java.net.http.HttpResponse; // Imports the HttpResponse class to handle and process HTTP responses from requests
import org.json.JSONObject; // Imports the JSONObject class for parsing and handling JSON data

public class WeatherApiClient {

  // Base URL for the Open-Meteo API
  private static final String API_URL = "https://api.open-meteo.com/v1/forecast";

  // HttpsClient instance for sending HTTP requests
  private final HttpClient httpClient;

  // Constructor to initialize HttpClient
  // This constructor is used for normal operation
  public WeatherApiClient() {
    this.httpClient = HttpClient.newHttpClient();
  }

  // Constructor to initialize HttpClient (used for testing)
  public WeatherApiClient(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  // Method to get weather data based on latitude and longitude
  public WeatherData getWeatherCoordinates(double latitude, double longitude) throws Exception {

    // Check that the coordinates are valid
    if (latitude > 90 || latitude < -90 || longitude > 180 || longitude < -180) {
      throw new IllegalArgumentException("Invalid coordinates");
    }

    // Construct the URL with query parameters for latitude, longitude, and current weather
    String url = API_URL + "?latitude=" + latitude + "&longitude=" + longitude + "&current_weather=true";

    // Build the HTTP request with the constructed URL
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

    // Send the HTTP request and receive the response as a string
    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    // Parse the response body and return the weather data
    return parseWeatherData(response.body());
  }

  // Method to parse the JSON response and create a WeatherData object
  private WeatherData parseWeatherData(String responseBody) {

    // Parse the response body into a JSONObject
    JSONObject json = new JSONObject(responseBody);

    // Extract the 'current_weather' object from the JSON response
    JSONObject currentWeather = json.getJSONObject("current_weather");

    // Retrieve temperature and windspeed from the 'current_weather' object
    double temperature = currentWeather.getDouble("temperature");
    double windspeed = currentWeather.getDouble("windspeed");

    // Create and return a new WeatherData object with the parsed values
    return new WeatherData(temperature, windspeed);
  }
}
