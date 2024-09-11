package com.krilevo.weatherapp;

import javafx.application.Application; // Import the Application class from JavaFX for creating a JavaFX application
import javafx.stage.Stage; // Import the Stage class from JavaFX to represent the main window of the application

public class WeatherApp extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Create an instance of the GUI class
    // WeatherAppGUI is a separate class responsible for creating and managing the user interface
    WeatherAppGUI gui = new WeatherAppGUI();

    // Set up the primary stage
    // primaryStage represents the main window of the application
    primaryStage.setTitle("Weather App"); // Set the title of the application window
    primaryStage.setScene(gui.createScene()); // Set the scene (UI layout) to the primary stage, created by the WeatherAppGUI class
    primaryStage.show(); // Display the application window
  }

  public static void main(String[] args) {
    // Launch the JavaFX application
    // This method initializes the JavaFX runtime and invokes the start method
    launch(args);
  }
}
