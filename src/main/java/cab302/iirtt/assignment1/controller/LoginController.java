package cab302.iirtt.assignment1.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button signInButton;
    @FXML private Button createAccountButton;

    @FXML
    public void initialize() {
        signInButton.setOnAction(e -> handleLogin());
    }

    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Both fields are required.");
            return;
        }

        if (authenticateUser(email, password)) {
            loadMainScene();
        } else {
            showAlert(Alert.AlertType.ERROR, "Invalid credentials. Please try again.");
        }
    }

    private boolean authenticateUser(String email, String password) {
        // TODO: Replace with real DB check
        return email.equals("test@student.com") && password.equals("password123");
    }

    private void loadMainScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/smartcookie/view/Main.fxml"));
            Stage stage = (Stage) emailField.getScene().getWindow();
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
