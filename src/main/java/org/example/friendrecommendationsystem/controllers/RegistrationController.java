package org.example.friendrecommendationsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.friendrecommendationsystem.database.DatabaseConnection;
import org.example.friendrecommendationsystem.model.FriendManager;
import org.example.friendrecommendationsystem.utilities.Utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationController {

    @FXML
    private TextArea aboutMe;

    @FXML
    private TextField addressField;

    @FXML
    private CheckBox captchaBox;

    @FXML
    private DatePicker dobPicker;

    @FXML
    private ComboBox<String> genderBox;

    @FXML
    private Button signInButton;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    public void initialize() {
        // Initialize gender combo-box
        genderBox.getItems().addAll("Male", "Female", "Other");

        // Listeners are Here:
        captchaBox.selectedProperty().addListener((observable, oldValue, newValue) -> toggleSubmitButton());
        nameField.textProperty().addListener((observable, oldValue, newValue) -> toggleSubmitButton());
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> toggleSubmitButton());
    }

    private void toggleSubmitButton() {
        boolean isAllFieldsFilled = !nameField.getText().isEmpty() &&
                !passwordField.getText().isEmpty() &&
                captchaBox.isSelected() &&
                genderBox.getValue() != null &&
                dobPicker.getValue() != null &&
                !addressField.getText().isEmpty() &&
                !aboutMe.getText().isEmpty();

        submitButton.setDisable(!isAllFieldsFilled);
    }

    @FXML
    private void handleSignInButton() throws IOException {
        // Navigate to Login form
        Stage currentStage = ( Stage ) signInButton.getScene().getWindow();
        Utils.changeScene(currentStage, "/views/LoginForm.fxml", "Login Form");
    }

    @FXML
    private void handleSubmitButton() {
        String username = nameField.getText();
        String password = passwordField.getText();
        String gender = genderBox.getValue();
        String address = addressField.getText();
        String about = aboutMe.getText();

        java.time.LocalDate dobLocalDate = dobPicker.getValue();
        java.sql.Date dob = (dobLocalDate != null) ? java.sql.Date.valueOf(dobLocalDate) : null;

        if (username.isEmpty() || password.isEmpty() || gender == null || dob == null
                || address.isEmpty() || about.isEmpty()) {
            System.out.println("All fields are required!");
            return;
        }

        String insertQuery = "INSERT INTO users(username, password, gender, dob, address, about_me) VALUE(?, ?, ?, ?, ?, ?)";

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, gender);
            preparedStatement.setDate(4, dob);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, about);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Registration successful!");
//                FriendManager.adjacencyList.put()
                handleSignInButton();
            } else {
                System.out.println("Failed to register.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}