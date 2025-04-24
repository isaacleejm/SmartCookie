package cab302.iirtt.assignment1;


import cab302.iirtt.assignment1.model.GeminiAPI;
import cab302.iirtt.assignment1.model.IUser;
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
        userDAO.start();

        // This should create the database (if the database has not yet been created) and create the studyGoals table (if studyGoals table has not yet been created)
        StudyGoalDAO studyGoalDAO = new StudyGoalDAO();
        studyGoalDAO.start();

        IUser.userRegistration("TestName", "LastName", "myUsername", "Password123"); // It works, but has not format checking
        IUser user = IUser.userLogin("johndoe", "password123"); // It works, it returns the user that was logged in, and returns null if failed


        launch();

    }
}