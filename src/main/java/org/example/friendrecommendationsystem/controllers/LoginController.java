package org.example.friendrecommendationsystem.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.friendrecommendationsystem.database.Database;
import org.example.friendrecommendationsystem.model.User;
import org.example.friendrecommendationsystem.utilities.Utils;

import java.io.IOException;

public class LoginController {

    @FXML
    private CheckBox captchaBox;

    @FXML
    private Button forgotPasswordField;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameField;

    public static User currentUser;

    public void initialize() {
        loginButton.setOnAction(event -> handleLogin());
        registerButton.setOnAction(event -> handleRegister());
        forgotPasswordField.setOnAction(event -> handleForgotPassword());
        captchaBox.setOnAction(event -> handleCaptchaCheck());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean isCaptchaChecked = captchaBox.isSelected();

        if (username.isEmpty() || password.isEmpty() || !isCaptchaChecked) {
            Utils.showAlert("Error", "All fields must be filled, and CAPTCHA must be checked!");
            usernameField.setText("");
            passwordField.setText("");
            captchaBox.setSelected(false);
            loginButton.setDisable(true);
            return;
        }

        if (Database.isValidLogin(username, password)) {
//            currentUser = new User(username, password);

            Stage currentStage = ( Stage ) loginButton.getScene().getWindow();
            Utils.changeScene(currentStage, "/views/DashboardForm.fxml", "Dashboard Form");
            System.out.println("Login successfully");
        } else {
            Utils.showAlert("Error", "Incorrect username or password!");
            usernameField.setText("");
            passwordField.setText("");
            captchaBox.setSelected(false);
            loginButton.setDisable(true);
        }
    }

    private void handleRegister() {
        try {
            // Load the Registration form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/RegistrationForm.fxml"));
            Parent registrationRoot = loader.load();

            // Get the current stage and set the new scene
            Stage stage = ( Stage ) registerButton.getScene().getWindow();
            Scene registrationScene = new Scene(registrationRoot);
            stage.setScene(registrationScene);

            // Optionally, set stage title
            stage.setTitle("Registration Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleForgotPassword() {
        // Handle forgot password logic
        System.out.println("Forgot Password button clicked!");
    }

    private void handleCaptchaCheck() {
        // Handle CAPTCHA checkbox logic
        if (captchaBox.isSelected()) {
            loginButton.setDisable(false);
        } else {
            loginButton.setDisable(true);
        }
    }
}
