package org.example.friendrecommendationsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.friendrecommendationsystem.database.Database;
import org.example.friendrecommendationsystem.utilities.Utils;

import static org.example.friendrecommendationsystem.controllers.LoginController.currentUser;

public class CreatePostController {

    @FXML
    private AnchorPane createPostPane;

    @FXML
    private Button postButton;

    @FXML
    private TextArea postContent;

    @FXML
    private TextField postTitle;

    @FXML
    private void initialize() {
        postButton.setOnAction(event -> handlePostButton());
    }

    private void handlePostButton() {
        String title = postTitle.getText().trim();
        String content = postContent.getText().trim();

        // Validate input
        if (title.isEmpty() || content.isEmpty()) {
            Utils.showAlert("Error", "Post title or content cannot be empty!");
            return;
        }

        int userId = currentUser.getUserId();
        String username = currentUser.getUsername();

        boolean success = Database.addPostToDatabase(title, content, userId, username);

        if (success) {
            System.out.println("Post added successfully!");

            // Clear the AnchorPane (reset UI)
            Utils.clearAnchorPane(createPostPane);
        } else {
            Utils.showAlert("Error", "Failed to add post to the database.");
        }
    }
}
