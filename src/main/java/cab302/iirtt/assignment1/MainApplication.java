package cab302.iirtt.assignment1;

import cab302.iirtt.assignment1.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.List;

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
        // The two lines below is used to test the Gemini API
//        GeminiAPI geminiAPI = new GeminiAPI();
//        String generatedResponse = geminiAPI.run("How does AI work? Please explain in less than 40 words.");
//        System.out.println(generatedResponse);

        // This should create the database (if the database has not yet been created) and create the users table (if users table has not yet been created)
        UserDAO userDAO = new UserDAO();
        userDAO.start();

        // This should create the database (if the database has not yet been created) and create the studyGoals table (if studyGoals table has not yet been created)
        StudyGoalDAO studyGoalDAO = new StudyGoalDAO();
        studyGoalDAO.start();

        // This should create the database (if the database has not yet been created) and create the AIResponses table (if aiResponses table has not yet been created)
        AIResponseDAO aiResponseDAO = new AIResponseDAO();
        aiResponseDAO.start();

        // This should create the database (if the database has not yet been created) and create the studyMaterials table (if studyMaterials table has not yet been created)
        StudyMaterialDAO studyMaterialDAO = new StudyMaterialDAO();
        studyMaterialDAO.start();


//        IUser.userRegistration("TestName", "LastName", "myUsername", "Password123"); // It works, but has not format checking
//        IUser user = IUser.userLogin("johndoe", "password123"); // It works, it returns the user that was logged in, and returns null if failed

//        Testing generateResponse
//        User user = IUser.userLogin("johndoe", "!Password123");
//        MainApplication.currentUser = user;
//        List<AIResponse> list = aiResponseDAO.getAllAIResponse();
//        for (AIResponse response : list) {
//            if (response instanceof FortuneCookie) {
//                response.generateResponse("", "neutral");
//            }
//        }


        launch();

    }
}
