package com.krilevo.weatherapp;

import javafx.scene.Scene; // Import the Scene class from JavaFX for setting up the UI layout
import javafx.scene.control.Button; // Import the Button class for creating buttons
import javafx.scene.control.Label; // Import the Label class for creating text labels
import javafx.scene.layout.VBox; // Import the VBox class for creating a vertical box layout

public class WeatherAppGUI {

  // Method to create and return a Scene object containing the UI layout
  public Scene createScene() {
    // Create a Label to instruct the user
    Label label = new Label("Click the button to fetch weather data.");

    // Create a Button that the user will click to fetch weather data
    Button button = new Button("Get Tampere Weather");

    // Create a Label to display weather data or error messages
    Label weatherLabel = new Label();

    // Define the action that occurs when the button is clicked
    button.setOnAction(event -> {
      try {
        // Create an instance of WeatherApiClient to fetch weather data
        WeatherApiClient client = new WeatherApiClient();
        
        // Get weather data for specific coordinates (Tampere, Finland)
        WeatherData weather = client.getWeatherCoordinates(61.497753, 23.760954);
        
        // Update the weatherLabel with the fetched weather data
        weatherLabel.setText("Temperature: " + weather.getTemperature() + "°C\n" +
                             "Windspeed: " + weather.getWindspeed() + " m/s");
      } catch (Exception e) {
        // Handle exceptions by displaying an error message
        weatherLabel.setText("Error fetching weather data: " + e.getMessage());
      }
    });

    // Create a VBox layout to arrange UI elements vertically with a spacing of 10 pixels
    VBox root = new VBox(10, label, button, weatherLabel);
    root.setAlignment(javafx.geometry.Pos.CENTER); // Center align all children within VBox
    root.setSpacing(10); // Add space between elements
    
    // Create a Scene object with the VBox layout, specifying the width and height of the scene
    Scene scene = new Scene(root, 600, 300);

    // Apply the stylesheet
    scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

    // Return the created Scene
    return scene;
  }
}
