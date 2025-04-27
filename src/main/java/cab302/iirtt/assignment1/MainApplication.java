package cab302.iirtt.assignment1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
        scene.setFill(Color.web("#f8f8f8"));
    }

    public static void main(String[] args) {
        GeminiAPI geminiAPI = new GeminiAPI();
        geminiAPI.run("How does AI work? Please explain in less than 40 words.");
        launch();

    }
}
