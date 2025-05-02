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
    @FXML private Label loginErrorLabel;

    @FXML
    public void initialize() {
        clearErrorLabels();
    }

    private void clearErrorLabels() {
        loginErrorLabel.setText("");
        loginErrorLabel.setOpacity(0);
    }

    @FXML
    private void onSignInButtonClick() {
        clearErrorLabels();
        User user = IUser.userLogin(usernameField.getText(), passwordField.getText());
        if (user != null) {
            System.out.println(user.getFirstName() + " " + user.getLastName() +" has successfuly Signed In!");
            MainApplication.currentUser = user;
            switchToDashboard();
        } else {
            loginErrorLabel.setText("Incorrect username or password");
            loginErrorLabel.setOpacity(1);
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