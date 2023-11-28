package com.example.rupizza;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * PizzaManagerMain class launches the Pizza Manager GUI.
 *
 * @author Pranay Bhatt and Fiona Wang
 */
public class PizzaManagerMain extends Application {

    /**
     * Starts Pizza Manager GUI
     * @param stage stage
     * @throws IOException ...
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PizzaManagerMain.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 450);
        stage.setTitle("RU Pizza <Main Menu>");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches Pizza Manager GUI
     * @param args ...
     */
    public static void main(String[] args) {
        launch();
    }
}