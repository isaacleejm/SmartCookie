package cab302.iirtt.assignment1;

import cab302.iirtt.assignment1.controller.AichatbotController;
import cab302.iirtt.assignment1.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.IOException;
//import java.util.List;

public class MainApplication extends Application {
    public static User currentUser = null; // Stores the current logged-in User

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("landingPage-view.fxml"));
        scene = new Scene(fxmlLoader.load(), 1280, 720);stage.setScene(scene);
        stage.setTitle("SmartCookie");
        stage.show();
        scene.setFill(Color.web("#f8f8f8"));
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {

        launch();

    }
}
