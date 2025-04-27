package cab302.iirtt.assignment1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;


public class LandingPageController {

    @FXML
    private void switchToRegister() throws IOException {
        MainApplication.setRoot("signUp-view");
    }

    @FXML
    private void switchToLogin() throws IOException {
        MainApplication.setRoot("login-view");
    }
}
