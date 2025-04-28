package cab302.iirtt.assignment1;

import cab302.iirtt.assignment1.model.AIResponseDAO;
import cab302.iirtt.assignment1.model.GeminiAPI;
import cab302.iirtt.assignment1.model.IUser;
import cab302.iirtt.assignment1.model.StudyGoalDAO;
import cab302.iirtt.assignment1.model.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.IOException;

public class MainApplication extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("dashboard-view.fxml"));
        scene = new Scene(fxmlLoader.load(), 1280, 720);stage.setScene(scene);
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


        // This should create the database (if the database has not yet been created) and create the AIResponses table (if aiResponses table has not yet been created)
        AIResponseDAO aiResponseDAO = new AIResponseDAO();

        launch();

    }
}
