package cab302.iirtt.assignment1.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

import cab302.iirtt.assignment1.model.IUser;
import cab302.iirtt.assignment1.model.UserDAO;

public class SignUpController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label messageLabel;

    @FXML
    private void initialize() {
        // No special initialization needed yet
    }

    @FXML
    private void onSignUpButtonClick() {
        if (setFirstNameField() && setLastNameField() && setUsernameField() && setPasswordField() && setConfirmPasswordField()) {
            IUser.userRegistration(firstNameField.getText().trim(), lastNameField.getText().trim(), usernameField.getText().trim(), passwordField.getText());
            messageLabel.setText("Account created successfully! Redirecting to login...");
            goToLogin();
        }
    }

    @FXML
    private void onGoBackButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/smartcookie/view/Main.fxml"));
            Stage stage = (Stage) firstNameField.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            messageLabel.setText("Failed to go back.");
            e.printStackTrace();
        }
    }

    private void goToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/smartcookie/view/login-view.fxml"));
            Stage stage = (Stage) firstNameField.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            messageLabel.setText("Failed to load login page.");
            e.printStackTrace();
        }
    }

    private boolean setFirstNameField() {
        String firstName = firstNameField.getText().trim();
        if (!firstName.matches("[a-zA-Z\\s]+")) {
            messageLabel.setText("First Name can only contain letters.");
            return false;
        }
        return true;
    }

    private boolean setLastNameField() {
        String lastName = lastNameField.getText().trim();
        if (!lastName.matches("[a-zA-Z\\s]+")) {
            messageLabel.setText("Last Name can only contain letters.");
            return false;
        }
        return true;
    }

    private boolean setUsernameField() {
        String username = usernameField.getText().trim();
        if (username.length() < 4 || !username.matches("[a-zA-Z0-9]+")) {
            messageLabel.setText("Username must be at least 4 characters and no spaces/symbols.");
            return false;
        }
        UserDAO userDAO = new UserDAO();
        if (userDAO.getUserByUsername(username) != null) {
            messageLabel.setText("Username already taken.");
            return false;
        }
        return true;
    }

    private boolean setPasswordField() {
        String password = passwordField.getText();
        if (password.length() < 8) {
            messageLabel.setText("Password must be at least 8 characters.");
            return false;
        }
        if (!password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*\\d.*") || !password.matches(".*[^a-zA-Z0-9].*")) {
            messageLabel.setText("Password must contain uppercase, lowercase, number, and special character.");
            return false;
        }
        if (password.contains(" ")) {
            messageLabel.setText("Password must not contain spaces.");
            return false;
        }
        return true;
    }

    private boolean setConfirmPasswordField() {
        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            messageLabel.setText("Passwords do not match.");
            return false;
        }
        return true;
    }
}