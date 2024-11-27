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
}
