package org.example.friendrecommendationsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.friendrecommendationsystem.utilities.SwitchScene;

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
    private Label usernameLabel;

    public void initialize() {
        logoutButton.setOnAction(event -> handleLogoutButton());
    }

    private void handleLogoutButton() {
        Stage currentStage = ( Stage ) logoutButton.getScene().getWindow();
        SwitchScene.changeScene(currentStage, "/views/LoginForm.fxml", "Login Form");
        System.out.println("Logout button clicked");
    }
}
