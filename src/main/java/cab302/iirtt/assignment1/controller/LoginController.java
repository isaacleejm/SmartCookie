package cab302.iirtt.assignment1.controller;

import cab302.iirtt.assignment1.MainApplication;
import cab302.iirtt.assignment1.model.IUser;
import cab302.iirtt.assignment1.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button signInButton;
    @FXML private Button createAccountButton;

    @FXML
    public void initialize() {

    }

    @FXML
    private void onSignInButtonClick() {
        User user = IUser.userLogin(usernameField.getText(), passwordField.getText());
        if (user != null) {
            System.out.println(user.getFirstName() + " " + user.getLastName() +" has successfuly Signed In!");
            // TODO: Open Dashboard page and pass the LoggedIn User there
        } else {
            System.out.println("Incorrect username or password");
            // TODO: Display error message on UI
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
