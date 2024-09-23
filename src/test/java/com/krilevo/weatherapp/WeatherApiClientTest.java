package com.krilevo.weatherapp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WeatherApiClientTest {

  // Instance of WeatherApiClient to be tested
  private WeatherApiClient weatherApiClient;
  
  // Mocked HttpClient to simulate HTTP requests and responses
  private HttpClient mockHttpClient;
  
  @BeforeEach
  public void setUp() {
    // Initialize the mock HttpClient before each test
    mockHttpClient = mock(HttpClient.class);
    
    // Initialize WeatherApiClient with the mocked HttpClient
    weatherApiClient = new WeatherApiClient(mockHttpClient);
  }
  
  @Test
  public void testGetWeatherCoordinates() throws Exception {
    // Create a mock JSON response
    String jsonResponse = "{\"current_weather\":{\"temperature\":23.8,\"windspeed\":11.9}}";
    
    // Mock HttpResponse to return the JSON response
    HttpResponse<String> mockResponse = mock(HttpResponse.class);
    when(mockResponse.body()).thenReturn(jsonResponse);
    
    // Configure the mock HttpClient to return the mock response for any HttpRequest
    when(mockHttpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString()))).thenReturn(mockResponse);
    
    // Call the method under test
    WeatherData weatherData = weatherApiClient.getWeatherCoordinates(61.497753, 23.760954);
    
    // Verify that the method returns expected results
    assertNotNull(weatherData, "WeatherData should not be null");
    assertEquals(23.8, weatherData.getTemperature(), 0.1, "Temperature should match the expected value");
    assertEquals(11.9, weatherData.getWindspeed(), 0.1, "Windspeed should match the expected value");
  }

  @Test
  public void testGetWeatherCoordinatesInvalidLatitude() {
    // Test method with an invalid latitude value
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      weatherApiClient.getWeatherCoordinates(1000, 21.518356);
    });

   // Verify that the correct exception is thrown with the expected message
   assertEquals("Invalid coordinates", exception.getMessage(), "Exception message should match");
  }
  
  @Test
  public void testGetWeatherCoordinatesInvalidLongitude() {
    // Test method with an invalid longitude value
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      weatherApiClient.getWeatherCoordinates(61.129417, 5000);
    });

    // Verify that the correct exception is thrown with the expected message
    assertEquals("Invalid coordinates", exception.getMessage(), "Exception message should match");
  }
}
