package cab302.iirtt.assignment1.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Renny was here! Tao was here too! Tom was here too!");
    }
}
