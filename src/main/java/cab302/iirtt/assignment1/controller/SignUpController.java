package cab302.iirtt.assignment1.controller;

import cab302.iirtt.assignment1.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

import cab302.iirtt.assignment1.model.IUser;
import cab302.iirtt.assignment1.model.UserDAO;

/**
 * Controller class for handling user interactions in the Sign-Up view.
 * Manages user input fields, validation logic, and navigation between views.
 */
public class SignUpController {

    /**
     * Text fields for user input.
     */
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;

    /**
     * Labels for displaying validation error messages.
     */
    @FXML private Label firstNameErrorLabel;
    @FXML private Label lastNameErrorLabel;
    @FXML private Label usernameErrorLabel;
    @FXML private Label passwordErrorLabel;
    @FXML private Label confirmPasswordErrorLabel;

    /**
     * Initializes the controller by clearing error labels and adding real-time validation listeners.
     */
    @FXML
    private void initialize() {
        clearErrorLabels();
        addRealTimeValidation();
    }

    /**
     * Handles the Go Back button click event.
     * Navigates the user back to the landing page view.
     */
    @FXML
    private void onGoBackButtonClick() {
        try {
            MainApplication.setRoot("landingPage-view");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Navigates the user to the Login view.
     */
    private void goToLogin() {
        try {
            MainApplication.setRoot("login-view");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds real-time validation listeners to all input fields.
     */
    private void addRealTimeValidation() {
        firstNameField.textProperty().addListener((obs, oldText, newText) -> setFirstNameField());
        lastNameField.textProperty().addListener((obs, oldText, newText) -> setLastNameField());
        usernameField.textProperty().addListener((obs, oldText, newText) -> setUsernameField());
        passwordField.textProperty().addListener((obs, oldText, newText) -> setPasswordField());
        confirmPasswordField.textProperty().addListener((obs, oldText, newText) -> setConfirmPasswordField());
    }

    /**
     * Handles the Sign-Up button click event.
     * Validates all fields and registers the user if inputs are valid.
     */
    @FXML
    private void onSignUpButtonClick() {
        clearErrorLabels();
        boolean valid = true;

        if (!setFirstNameField()) valid = false;
        if (!setLastNameField()) valid = false;
        if (!setUsernameField()) valid = false;
        if (!setPasswordField()) valid = false;
        if (!setConfirmPasswordField()) valid = false;

        if (valid) {
            IUser.userRegistration(
                    firstNameField.getText().trim(),
                    lastNameField.getText().trim(),
                    usernameField.getText().trim(),
                    passwordField.getText()
            );
            goToLogin();
        }
    }

    /**
     * Validates the First Name field.
     * @return true if valid, false otherwise.
     */
    private void clearErrorLabels() {
        firstNameErrorLabel.setText(""); firstNameErrorLabel.setOpacity(0);
        lastNameErrorLabel.setText(""); lastNameErrorLabel.setOpacity(0);
        usernameErrorLabel.setText(""); usernameErrorLabel.setOpacity(0);
        passwordErrorLabel.setText(""); passwordErrorLabel.setOpacity(0);
        confirmPasswordErrorLabel.setText(""); confirmPasswordErrorLabel.setOpacity(0);
    }

    /**
     * Validates the First Name field.
     * @return true if valid, false otherwise.
     */
    private boolean setFirstNameField() {
        String firstName = firstNameField.getText().trim();
        if (!firstName.matches("[a-zA-Z\\s]+")) {
            firstNameErrorLabel.setText("First Name can only contain letters.");
            firstNameErrorLabel.setOpacity(1);
            return false;
        }
        firstNameErrorLabel.setText("");
        firstNameErrorLabel.setOpacity(0);
        return true;
    }

    /**
     * Validates the Last Name field.
     * @return true if valid, false otherwise.
     */
    private boolean setLastNameField() {
        String lastName = lastNameField.getText().trim();
        if (!lastName.matches("[a-zA-Z\\s]+")) {
            lastNameErrorLabel.setText("Last Name can only contain letters.");
            lastNameErrorLabel.setOpacity(1);
            return false;
        }
        lastNameErrorLabel.setText("");
        lastNameErrorLabel.setOpacity(0);
        return true;
    }

    /**
     * Validates the Username field.
     * @return true if valid, false otherwise.
     */
    private boolean setUsernameField() {
        String username = usernameField.getText().trim();
        if (username.length() < 4 || !username.matches("[a-zA-Z0-9]+")) {
            usernameErrorLabel.setText("Username must be at least 4 characters and no spaces/symbols.");
            usernameErrorLabel.setOpacity(1);
            return false;
        }
        UserDAO userDAO = new UserDAO();
        if (userDAO.getUserByUsername(username) != null) {
            usernameErrorLabel.setText("Username already taken.");
            usernameErrorLabel.setOpacity(1);
            return false;
        }
        usernameErrorLabel.setText("");
        usernameErrorLabel.setOpacity(0);
        return true;
    }

    /**
     * Validates the Password field.
     * @return true if valid, false otherwise.
     */
    private boolean setPasswordField() {
        String password = passwordField.getText();
        if (password.length() < 8) {
            passwordErrorLabel.setText("Password must be at least 8 characters.");
            passwordErrorLabel.setOpacity(1);
            return false;
        }
        if (!password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*\\d.*") || !password.matches(".*[^a-zA-Z0-9].*")) {
            passwordErrorLabel.setText("Password must contain uppercase, lowercase, number, and special character.");
            passwordErrorLabel.setOpacity(1);
            return false;
        }
        if (password.contains(" ")) {
            passwordErrorLabel.setText("Password must not contain spaces.");
            passwordErrorLabel.setOpacity(1);
            return false;
        }
        passwordErrorLabel.setText("");
        passwordErrorLabel.setOpacity(0);
        return true;
    }

    /**
     * Validates the Confirm Password field.
     * @return true if passwords match, false otherwise.
     */
    private boolean setConfirmPasswordField() {
        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            confirmPasswordErrorLabel.setText("Passwords do not match.");
            confirmPasswordErrorLabel.setOpacity(1);
            return false;
        }
        confirmPasswordErrorLabel.setText("");
        confirmPasswordErrorLabel.setOpacity(0);
        return true;
    }
}