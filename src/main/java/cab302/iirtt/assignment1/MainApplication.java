package cab302.iirtt.assignment1;


import cab302.iirtt.assignment1.model.GeminiAPI;
import cab302.iirtt.assignment1.model.StudyGoalDAO;
import cab302.iirtt.assignment1.model.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 700);
        stage.setTitle("Hello There!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // The two lines below is used to test the Gemini API
        GeminiAPI geminiAPI = new GeminiAPI();
        geminiAPI.run("How does AI work? Please explain in less than 40 words.");

        // This should create the database (if the database has not yet been created) and create the users table (if users table has not yet been created)
        UserDAO userDAO = new UserDAO();

        // This should create the database (if the database has not yet been created) and create the studyGoals table (if studyGoals table has not yet been created)
        StudyGoalDAO studyGoalDAO = new StudyGoalDAO();

        launch();

    }
}