package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BreathingMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            //the root of the scene shown in the main window
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            //create the scene specifying the root
            Scene scene = new Scene(root);

            primaryStage.setTitle("Buteyko Breathing Exercise");
            //add scene to the stage
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            //make the stage visible
            primaryStage.show();

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch the application
        launch(args);
    }

}
