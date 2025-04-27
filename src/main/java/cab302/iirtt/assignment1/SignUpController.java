package cab302.iirtt.assignment1;

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

    @FXML private Label firstNameErrorLabel;
    @FXML private Label lastNameErrorLabel;
    @FXML private Label usernameErrorLabel;
    @FXML private Label passwordErrorLabel;
    @FXML private Label confirmPasswordErrorLabel;

    @FXML
    private void initialize() {
        clearErrorLabels();
    }

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

    @FXML
    private void onGoBackButtonClick() {
        try {
            MainApplication.setRoot("Landing-Page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cab302/iirtt/assignment1/login-view.fxml"));
            Stage stage = (Stage) firstNameField.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearErrorLabels() {
        firstNameErrorLabel.setText(""); firstNameErrorLabel.setOpacity(0);
        lastNameErrorLabel.setText(""); lastNameErrorLabel.setOpacity(0);
        usernameErrorLabel.setText(""); usernameErrorLabel.setOpacity(0);
        passwordErrorLabel.setText(""); passwordErrorLabel.setOpacity(0);
        confirmPasswordErrorLabel.setText(""); confirmPasswordErrorLabel.setOpacity(0);
    }

    private boolean setFirstNameField() {
        String firstName = firstNameField.getText().trim();
        if (!firstName.matches("[a-zA-Z\\s]+")) {
            firstNameErrorLabel.setText("First Name can only contain letters.");
            firstNameErrorLabel.setOpacity(1);
            return false;
        }
        return true;
    }

    private boolean setLastNameField() {
        String lastName = lastNameField.getText().trim();
        if (!lastName.matches("[a-zA-Z\\s]+")) {
            lastNameErrorLabel.setText("Last Name can only contain letters.");
            lastNameErrorLabel.setOpacity(1);
            return false;
        }
        return true;
    }

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
        return true;
    }

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
        return true;
    }

    private boolean setConfirmPasswordField() {
        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            confirmPasswordErrorLabel.setText("Passwords do not match.");
            confirmPasswordErrorLabel.setOpacity(1);
            return false;
        }
        return true;
    }
}
