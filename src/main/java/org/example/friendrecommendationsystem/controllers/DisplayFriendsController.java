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

import java.util.ArrayList;

import static org.example.friendrecommendationsystem.controllers.LoginController.currentUser;

public class DisplayFriendsController {

    @FXML
    private ListView<String> friendsList;

    public void initialize() {
        // Load users from the database
        ArrayList<String> users = Database.getFriends(currentUser.getUserId());
        ObservableList<String> friendsObservableList = FXCollections.observableArrayList(users);

        friendsList.setItems(friendsObservableList);

        // Customizing each row
        friendsList.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new ListCell<String>() {
                    private final Text username = new Text();
                    private final Button unfriendButton = new Button("Unfriend");
                    private final HBox content = new HBox();

                    {
                        // Styling the username text
                        username.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #333333;");
                        username.setWrappingWidth(200); // Fixed width to align the button

                        // Styling the Unfriend button
                        unfriendButton.setStyle("-fx-background-color: #FF6347; -fx-text-fill: white; -fx-cursor: hand; -fx-font-size: 14px; -fx-padding: 5 10;");

                        // Handling Unfriend button click
                        unfriendButton.setOnAction(event -> {
                            String friend = getItem();
                            if (friend != null) {
                                // Perform unfriend operation
                                Database.removeFriendship(currentUser.getUserId(), friend); // Call removeFriendship method
                                friendsList.getItems().remove(friend); // Remove friend from the list
                                System.out.println("Unfriended: " + friend);
                            }
                        });

                        // Configure the layout
                        content.setSpacing(10);
                        content.setStyle("-fx-padding: 10; -fx-background-color: #f4f4f4; -fx-border-color: #dddddd;");

                        // Add spacing to ensure alignment
                        HBox.setHgrow(username, Priority.ALWAYS);
                        HBox.setHgrow(unfriendButton, Priority.NEVER);
                        content.getChildren().addAll(username, unfriendButton);
                    }

                    @Override
                    protected void updateItem(String friend, boolean empty) {
                        super.updateItem(friend, empty);
                        if (empty || friend == null) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            username.setText(friend); // Show the user's username
                            setGraphic(content);
                        }
                    }
                };
            }
        });
    }
}
