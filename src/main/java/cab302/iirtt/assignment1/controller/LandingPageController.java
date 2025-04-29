package cab302.iirtt.assignment1.controller;

import cab302.iirtt.assignment1.MainApplication;
import javafx.fxml.FXML;

import java.io.IOException;


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
