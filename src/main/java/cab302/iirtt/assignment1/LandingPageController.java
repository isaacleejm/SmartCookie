package cab302.iirtt.assignment1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;


public class LandingPageController {
    private void switchToRegister(ActionEvent event) throws IOException {
        // Loads signUp-view.fxml
        Parent signup = FXMLLoader.load(getClass().getResource("signUp-view.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Sets the sign-up scene
        stage.setScene(new Scene(signup));
        stage.show();
    }

    private void switchToLogin(ActionEvent event) throws IOException {
        // Loads login-view.fxml
        Parent login = FXMLLoader.load(getClass().getResource("login-view.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Sets the login scene
        stage.setScene(new Scene(login));
        stage.show();
    }
}
