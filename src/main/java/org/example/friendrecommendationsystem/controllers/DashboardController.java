package org.example.friendrecommendationsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.friendrecommendationsystem.utilities.Utils;

public class DashboardController {

    @FXML
    private Button addFriendButton;

    @FXML
    private Button createPostsButton;

    @FXML
    private Button displayAllFriendsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button recommendFriendsButton;

    @FXML
    private Label userMailLabel;

    @FXML
    private AnchorPane rightPane;

    @FXML
    private Label usernameLabel;

    public void initialize() {
        logoutButton.setOnAction(event -> handleLogoutButton());
        createPostsButton.setOnAction(event -> handleCreatePostButton());
//        usernameLabel.setText(currentUser);
    }

    private void handleLogoutButton() {
        Stage currentStage = ( Stage ) logoutButton.getScene().getWindow();
        Utils.changeScene(currentStage, "/views/LoginForm.fxml", "Login Form");
        System.out.println("Logout button clicked");
    }

    private void handleAddFriend() {
        System.out.println("Logout button clicked");
    }

    private void handleCreatePostButton() {
//        Stage currentStage = (Stage) createPostsButton.getScene().getWindow();
//        Utils.openNewWindow(currentStage, "/views/CreatePostForm.fxml", "Create Post");
//        System.out.println("Create Post button clicked");

        Utils.loadAnchorPaneContent(rightPane, "/views/CreatePostForm.fxml");
    }
}
