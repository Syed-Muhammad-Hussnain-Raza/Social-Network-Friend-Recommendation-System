package org.example.friendrecommendationsystem.utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SwitchScene {

    public static void changeScene(Stage currentStage, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SwitchScene.class.getResource(fxmlPath));
            Scene newScene = new Scene(loader.load());

            currentStage.setScene(newScene);
            currentStage.setTitle(title);
            currentStage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to open a new scene in a new window (without closing the current one)
    public static void openNewWindow(Stage currentStage, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SwitchScene.class.getResource(fxmlPath));
            Scene newScene = new Scene(loader.load());

            // Create a new Stage for the new scene
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.setTitle(title);
            newStage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
