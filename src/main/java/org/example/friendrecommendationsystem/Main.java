package org.example.friendrecommendationsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        
        stage.setTitle("Login Form");
        stage.setScene(scene);
        stage.show();
    }

    // Main function:
    public static void main(String[] args) {
        // It is the first screen of our application.
        launch();
    }
}