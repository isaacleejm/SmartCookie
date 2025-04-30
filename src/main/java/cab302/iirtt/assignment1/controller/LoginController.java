package cab302.iirtt.assignment1.controller;

import cab302.iirtt.assignment1.MainApplication;
import cab302.iirtt.assignment1.model.IUser;
import cab302.iirtt.assignment1.model.User;
import cab302.iirtt.assignment1.model.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controller for handling user login interactions.
 */
public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button signInButton;
    @FXML private Button createAccountButton;

    /**
     * Error label for invalid login feedback.
     */
    @FXML private Label usernameErrorLabel;
    @FXML private Label passwordErrorLabel;

    @FXML
    public void initialize() {
        clearErrorLabels();
    }

    private void clearErrorLabels() {
        usernameErrorLabel.setText("");
        usernameErrorLabel.setOpacity(0);
        passwordErrorLabel.setText("");
        passwordErrorLabel.setOpacity(0);
    }

    @FXML
    private void onSignInButtonClick() {
        clearErrorLabels();
        boolean valid = true;

        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername(username);

        if (user == null) {
            usernameErrorLabel.setText("Incorrect username");
            usernameErrorLabel.setOpacity(1);
            valid = false;
        }

        if (user != null && !user.getPassword().equals(password)) {
            passwordErrorLabel.setText("Incorrect password");
            passwordErrorLabel.setOpacity(1);
            valid = false;
        }

        if (valid) {
            System.out.println(user.getFirstName() + " " + user.getLastName() + " has successfully Signed In!");
            // TODO: Load Dashboard Scene
        }
    }

    @FXML
    private void onCreateAccountButtonClick() {
        try {
            MainApplication.setRoot("signUp-view");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMainScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/smartcookie/view/Main.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Failed to load main application.");
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
