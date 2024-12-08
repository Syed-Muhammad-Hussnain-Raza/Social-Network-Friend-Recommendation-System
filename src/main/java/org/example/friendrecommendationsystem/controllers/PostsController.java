package org.example.friendrecommendationsystem.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.friendrecommendationsystem.database.Database;
import org.example.friendrecommendationsystem.model.Post;

public class PostsController {

    @FXML
    private VBox postsListView;  // VBox to hold the list of posts

    // This method is called when the FXML is loaded
    @FXML
    public void initialize() {
        // Fetch posts from the service
        ObservableList<Post> posts = FXCollections.observableArrayList(Database.getPosts());

        // Loop through the posts and add them to the VBox
        for (Post post : posts) {
            VBox postVBox = createPostBox(post);
            postsListView.getChildren().add(postVBox);
        }
    }

    // Create a VBox for each post, containing its title, content, and username
    private VBox createPostBox(Post post) {
        VBox postVBox = new VBox(5);  // Space between elements
        postVBox.setStyle("-fx-padding: 10; -fx-border-color: #ccc; -fx-border-radius: 5px; -fx-background-color: #f9f9f9;");

        // Title
        Label titleLabel = new Label(post.getTitle());
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Content (Wrapped Text)
        Text contentText = new Text(post.getContent());
        contentText.setStyle("-fx-font-size: 14px;");
        contentText.setWrappingWidth(740);  // Set wrapping width to fit within the screen width

        // Username and Timestamp (optional)
        Label userLabel = new Label("Posted by " + post.getUsername() + " on " + post.getCreatedAt());
        userLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;");

        // Add components to the VBox
        postVBox.getChildren().addAll(titleLabel, contentText, userLabel);

        return postVBox;
    }
}
