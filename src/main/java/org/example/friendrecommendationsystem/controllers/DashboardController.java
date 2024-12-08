package org.example.friendrecommendationsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.friendrecommendationsystem.model.FriendManager;
import org.example.friendrecommendationsystem.utilities.Utils;

import static org.example.friendrecommendationsystem.controllers.LoginController.currentUser;

public class DashboardController {
    @FXML
    private Label usernameLabel, aboutLabel;

    @FXML
    private Button addFriendButton, displayAllFriendsButton, recommendFriendsButton,
            createPostsButton, logoutButton, logoutFromMenu;

    @FXML
    private MenuItem viewProfile, editProfile;

    @FXML
    private MenuItem myPosts, friendsPost, allPosts;

    @FXML
    private MenuItem displayAllFriendsMenu, mutualFriendsMenu, recommendedFriendMenu;


    @FXML
    private AnchorPane rightPane;

    private FriendManager friendManager;

    public void initialize() {
        usernameLabel.setText(currentUser.getUsername());
        aboutLabel.setText(currentUser.getAboutMe());
        addFriendButton.setOnAction(event -> handleAddFriend());
        displayAllFriendsButton.setOnAction(actionEvent -> displayFriends());
        recommendFriendsButton.setOnAction(event -> handleRecommendedFriend());
        createPostsButton.setOnAction(event -> handleCreatePostButton());
        logoutButton.setOnAction(event -> handleLogoutButton());

        // Menu
        logoutFromMenu.setOnAction(event -> handleLogoutButton());
        displayAllFriendsMenu.setOnAction(actionEvent -> displayFriends());
        recommendedFriendMenu.setOnAction(event -> handleRecommendedFriend());
        mutualFriendsMenu.setOnAction(event -> System.out.println("Mutual Button Clicked!"));

        allPosts.setOnAction(event -> handleDisplayAllPosts());
    }

    private void handleLogoutButton() {
        Stage currentStage = ( Stage ) logoutButton.getScene().getWindow();
        Utils.changeScene(currentStage, "/views/LoginForm.fxml", "Login Form");
        System.out.println("Logout button clicked");
    }

    private void handleAddFriend() {
        Utils.loadAnchorPaneContent(rightPane, "/views/AddFriendsForm.fxml");
    }

    private void handleRecommendedFriend() {
        Utils.loadAnchorPaneContent(rightPane, "/views/RecommendedFriendsForm.fxml");
    }

    private void handleCreatePostButton() {
        Utils.loadAnchorPaneContent(rightPane, "/views/CreatePostForm.fxml");
    }

    private void displayFriends() {
        Utils.loadAnchorPaneContent(rightPane, "/views/DisplayFriendsForm.fxml");
    }

    private void handleDisplayAllPosts() {
        Utils.loadAnchorPaneContent(rightPane, "/views/PostsForm.fxml");
    }
}
