package org.example.friendrecommendationsystem.utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class Utils {

    public static void changeScene(Stage currentStage, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(Utils.class.getResource(fxmlPath));
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
            FXMLLoader loader = new FXMLLoader(Utils.class.getResource(fxmlPath));
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

    public static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void loadAnchorPaneContent(Pane pane, String fxmlPath) {
        try {
            // Load the FXML content
            Pane content = FXMLLoader.load(Utils.class.getResource(fxmlPath));

            // Clear existing children and add new content
            pane.getChildren().setAll(content);

            // If you're dealing with an AnchorPane, bind it to the full size of the pane
            if (pane instanceof AnchorPane) {
                AnchorPane.setTopAnchor(content, 0.0);
                AnchorPane.setBottomAnchor(content, 0.0);
                AnchorPane.setLeftAnchor(content, 0.0);
                AnchorPane.setRightAnchor(content, 0.0);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load FXML: " + fxmlPath);
        }
    }

//    public static void loadAnchorPaneContent(AnchorPane pane, String fxmlPath) {
//        try {
//            // Load the FXML content
//            AnchorPane content = FXMLLoader.load(Utils.class.getResource(fxmlPath));
//
//            // Clear existing children and add new content
//            pane.getChildren().setAll(content);
//
//            // Bind the FXML content to fill the entire AnchorPane
//            AnchorPane.setTopAnchor(content, 0.0);
//            AnchorPane.setBottomAnchor(content, 0.0);
//            AnchorPane.setLeftAnchor(content, 0.0);
//            AnchorPane.setRightAnchor(content, 0.0);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Failed to load FXML: " + fxmlPath);
//        }
//    }

    public static void clearAnchorPane(AnchorPane pane) {
        if (pane != null)
            pane.getChildren().clear();
    }
}
