package org.example.friendrecommendationsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.friendrecommendationsystem.database.Database;
import org.example.friendrecommendationsystem.model.User;
import org.example.friendrecommendationsystem.utilities.Utils;

import java.util.List;

import static org.example.friendrecommendationsystem.controllers.LoginController.currentUser;

public class AddFriendsController {

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private ListView<String> friendsListView;

    @FXML
    private TextField addFriendField;

    @FXML
    private Button addFriendButton;

    @FXML
    private Button refreshButton;

    private User loggedInUser;

    public void initialize() {
        // Load the current user's friends when the view loads
        loggedInUser = currentUser;
        refreshFriendsList();

        searchButton.setOnAction(event -> handleSearch());
        addFriendButton.setOnAction(event -> handleAddFriend());
        refreshButton.setOnAction(event -> refreshFriendsList());
    }

    private void handleSearch() {
        String username = searchField.getText().trim();
        if (username.isEmpty()) {
            Utils.showAlert("Error", "Search field cannot be empty.");
            return;
        }

        boolean userExists = Database.isUserExists(username);
        if (userExists) {
            Utils.showAlert("Success", "User " + username + " exists!");
        } else {
            Utils.showAlert("Error", "User " + username + " not found!");
        }
    }

    private void handleAddFriend() {
        String friendUsername = addFriendField.getText().trim();

        if (friendUsername.isEmpty()) {
            Utils.showAlert("Error", "Friend username cannot be empty.");
            return;
        }

        if (loggedInUser != null) {
            boolean added = Database.addFriend(loggedInUser.getUserId(), friendUsername);

            if (added) {
                Utils.showAlert("Success", friendUsername + " has been added as your friend.");
                refreshFriendsList();
            } else {
                Utils.showAlert("Error", "Failed to add " + friendUsername + " as a friend.");
            }
        } else {
            Utils.showAlert("Error", "User is not logged in.");
        }
    }

    private void refreshFriendsList() {
        if (loggedInUser != null) {
            List<String> friends = Database.getFriends(loggedInUser.getUserId());
            friendsListView.getItems().setAll(friends);
        }
    }
}
