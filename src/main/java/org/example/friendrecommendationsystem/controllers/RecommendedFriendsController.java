package org.example.friendrecommendationsystem.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.example.friendrecommendationsystem.database.Database;

import static org.example.friendrecommendationsystem.controllers.LoginController.currentUser;

import java.util.ArrayList;

public class RecommendedFriendsController {

    @FXML
    private ListView<String> recommendedFriendsList;

    public void initialize() {
        // Get the recommended friends for the current user
        ArrayList<String> recommendedFriends = Database.getRecommendedFriends(currentUser.getUserId());

        // Create an observable list from the recommended friends
        ObservableList<String> recommendedFriendsObservableList = FXCollections.observableArrayList(recommendedFriends);

        // Set the list items in the ListView
        recommendedFriendsList.setItems(recommendedFriendsObservableList);

        // Customizing each row in the ListView
        recommendedFriendsList.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new ListCell<String>() {
                    private final Text username = new Text();
                    private final Button addFriendButton = new Button("Add Friend");
                    private final HBox content = new HBox();

                    {
                        // Styling the username text
                        username.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #333333;");
                        username.setWrappingWidth(200); // Fixed width to align the button

                        // Styling the Add Friend button
                        addFriendButton.setStyle("-fx-background-color: #32CD32; -fx-text-fill: white; -fx-cursor: hand; -fx-font-size: 14px; -fx-padding: 5 10;");

                        // Handling Add Friend button click
                        addFriendButton.setOnAction(event -> {
                            String recommendedFriend = getItem();
                            if (recommendedFriend != null) {
                                // Add friend operation (Add to database)
                                Database.addFriend(currentUser.getUserId(), recommendedFriend); // Assuming you have a method to add a friend in the database
                                recommendedFriendsList.getItems().remove(recommendedFriend); // Remove the friend from the list
                                System.out.println("Added Friend: " + recommendedFriend);
                            }
                        });

                        // Configure the layout
                        content.setSpacing(10);
                        content.setStyle("-fx-padding: 10; -fx-background-color: #f4f4f4; -fx-border-color: #dddddd;");

                        // Add spacing to ensure alignment
                        HBox.setHgrow(username, Priority.ALWAYS);
                        HBox.setHgrow(addFriendButton, Priority.NEVER);
                        content.getChildren().addAll(username, addFriendButton);
                    }

                    @Override
                    protected void updateItem(String recommendedFriend, boolean empty) {
                        super.updateItem(recommendedFriend, empty);
                        if (empty || recommendedFriend == null) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            username.setText(recommendedFriend); // Show the user's username
                            setGraphic(content);
                        }
                    }
                };
            }
        });
    }
}
