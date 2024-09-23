package com.krilevo.weatherapp;

import javafx.geometry.Insets;
import javafx.scene.Scene; // Import the Scene class from JavaFX for setting up the UI layout
import javafx.scene.control.Button; // Import the Button class for creating buttons
import javafx.scene.control.Label; // Import the Label class for creating text labels
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox; // Import the VBox class for creating a vertical box layout

public class WeatherAppGUI {

  // Method to create and return a Scene object containing the UI layout
  public Scene createScene() {
    // Set up the UI elements
    Label instructionLabel = new Label("Enter latitude and longitude to fetch weather data:");
    TextField latitudeField = new TextField();
    latitudeField.setPromptText("Latitude");
    TextField longitudeField = new TextField();
    longitudeField.setPromptText("Longitude");
    Button button = new Button("Get Weather");
    Label weatherLabel = new Label();

    // Button action to fetch the weather data
    button.setOnAction(event -> {
      try {
        double latitude = Double.parseDouble(latitudeField.getText());
        double longitude = Double.parseDouble(longitudeField.getText());
        
        WeatherApiClient client = new WeatherApiClient();
        WeatherData weather = client.getWeatherCoordinates(latitude, longitude);
        weatherLabel.setText("Temperature: " + weather.getTemperature() + "°C\n" +
                             "Windspeed: " + weather.getWindspeed() + " m/s");
      } catch (NumberFormatException e) {
        weatherLabel.setText("Please enter valid latitude and longitude.");
      } catch (Exception e) {
        weatherLabel.setText("Error fetching weather data: " + e.getMessage());
      }
    });

    VBox root = new VBox(10, instructionLabel, latitudeField, longitudeField, button, weatherLabel);
    root.setPadding(new Insets(15, 15, 15, 15)); // Top, Right, Bottom, Left padding
    Scene scene = new Scene(root, 600, 300);

    // Apply the stylesheet
    scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

    return scene;
  }
}
