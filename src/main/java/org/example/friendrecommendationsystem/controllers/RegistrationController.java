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
import org.example.friendrecommendationsystem.utilities.SwitchScene;

import java.io.IOException;

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
        genderBox.getItems().addAll("Male", "Female", "Other");

        // Listeners are Here:
        captchaBox.selectedProperty().addListener((observable, oldValue, newValue) -> toggleSubmitButton());
        nameField.textProperty().addListener((observable, oldValue, newValue) -> toggleSubmitButton());
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> toggleSubmitButton());
    }

    private void toggleSubmitButton() {
        boolean isAllFieldsFilled = !nameField.getText().isEmpty() &&
                !passwordField.getText().isEmpty() &&
                captchaBox.isSelected();

        submitButton.setDisable(!isAllFieldsFilled);
    }

    @FXML
    private void handleSignInButton() throws IOException {
        // Load the Registration form
        Stage currentStage = ( Stage ) signInButton.getScene().getWindow();
        SwitchScene.changeScene(currentStage, "/views/LoginForm.fxml", "Login Form");
    }
}
