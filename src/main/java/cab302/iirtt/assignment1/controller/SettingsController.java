package cab302.iirtt.assignment1.controller;

import cab302.iirtt.assignment1.MainApplication;
import cab302.iirtt.assignment1.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.animation.AnimationTimer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class SettingsController {

    protected static int sessionDuration;
    private long startTime;
    private AnimationTimer timer;

    @FXML private Text usernameText;
    @FXML private Text goalsCompletedText;
    @FXML private Text sessionDurationText;
    @FXML private Text responsesGatheredText;

    @FXML
    private void switchToDashboard() throws IOException {
        MainApplication.setRoot("dashboard-view");
    }

    @FXML
    private void switchToUploadMaterial() throws IOException {
        MainApplication.setRoot("uploadMaterial-view");
    }

    @FXML
    private void switchToNotifications() throws IOException {
        MainApplication.setRoot("notifications-view");
    }

    @FXML
    private void switchToAIChatbot() throws IOException {
        MainApplication.setRoot("aichatbot-view");
    }

    @FXML
    private void switchToStudyGoals() throws IOException {
        MainApplication.setRoot("studyGoals-view");
    }

    @FXML
    private void switchToMyFortune() throws IOException {
        MainApplication.setRoot("myFortune-view");
    }

    @FXML
    private void switchToAdvice() throws IOException {
        MainApplication.setRoot("adviceTips-view");
    }

    @FXML
    private void switchToSettings() throws IOException {
        MainApplication.setRoot("settings-view");
    }

    // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Alert.html
    @FXML
    private void logoutConfirm() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Logout");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            MainApplication.currentUser = null;
            MainApplication.setRoot("landingPage-view");
        }
    }

    // Hover for Left Navigation Bar
    @FXML
    private Rectangle dashboard; // fx:id in Scene Builder
    @FXML
    private Rectangle uploadmaterial;
    @FXML
    private Rectangle notifications;
    @FXML
    private Rectangle aichatbot;
    @FXML
    private Rectangle studygoals;
    @FXML
    private Rectangle myfortunes;
    @FXML
    private Rectangle advice;
    @FXML
    private Rectangle settings;
    @FXML
    private Rectangle logout;



    @FXML
    public void initialize() {
        Color originalColor = (Color) dashboard.getFill();

        // Hover effect
        dashboard.setOnMouseEntered(e -> dashboard.setFill(Color.web("#0088ff1a")));
        dashboard.setOnMouseExited(e -> dashboard.setFill(Color.web("#0088ff00")));
        uploadmaterial.setOnMouseEntered(e -> uploadmaterial.setFill(Color.web("#0088ff1a")));
        uploadmaterial.setOnMouseExited(e -> uploadmaterial.setFill(Color.web("#0088ff00")));
        notifications.setOnMouseEntered(e -> notifications.setFill(Color.web("#0088ff1a")));
        notifications.setOnMouseExited(e -> notifications.setFill(Color.web("#0088ff00")));
        aichatbot.setOnMouseEntered(e -> aichatbot.setFill(Color.web("#0088ff1a")));
        aichatbot.setOnMouseExited(e -> aichatbot.setFill(Color.web("#0088ff00")));
        studygoals.setOnMouseEntered(e -> studygoals.setFill(Color.web("#0088ff1a")));
        studygoals.setOnMouseExited(e -> studygoals.setFill(Color.web("#0088ff00")));
        myfortunes.setOnMouseEntered(e -> myfortunes.setFill(Color.web("#0088ff1a")));
        myfortunes.setOnMouseExited(e -> myfortunes.setFill(Color.web("#0088ff00")));
        advice.setOnMouseEntered(e -> advice.setFill(Color.web("#0088ff1a")));
        advice.setOnMouseExited(e -> advice.setFill(Color.web("#0088ff00")));
        settings.setOnMouseEntered(e -> settings.setFill(Color.web("#0088ff1a")));
        settings.setOnMouseExited(e -> settings.setFill(Color.web("#0088ff00")));
        logout.setOnMouseEntered(e -> logout.setFill(Color.web("#0088ff1a")));
        logout.setOnMouseExited(e -> logout.setFill(Color.web("#0088ff00")));

        // Display Logged-in User data
        StudyGoalDAO studyGoalDAO = new StudyGoalDAO();
        AIResponseDAO aiResponseDAO = new AIResponseDAO();
        User user = MainApplication.currentUser;

        List<StudyGoal> goalsCompletedList = studyGoalDAO.getCompletedStudyGoalsByUserID(user.getUserID());
        int goalsCompleted = goalsCompletedList.size();
        goalsCompletedText.setText(Integer.toString(goalsCompleted));

        List<AIResponse> responsesGatheredList = aiResponseDAO.getAIResponsesByUserID(user.getUserID());
        int responsesGathered = responsesGatheredList.size();
        responsesGatheredText.setText(Integer.toString(responsesGathered));

        String username = user.getUsername();
        usernameText.setText(username);

        startTimer(); // starts the session timer
        // TODO: This method is currently resetting every time dashboard runs
    }

    public void startTimer() {
        startTime = System.nanoTime();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long elapsedNanos = now - startTime;
                double elapsedSeconds = elapsedNanos / 1_000_000_000.0;
                long elapsedMinutes = Math.round(elapsedSeconds / 60);
                sessionDurationText.setText(String.format("%d", elapsedMinutes) + " m");
            }
        };
        timer.start();
    }

    @FXML
    private void deleteButtonOnClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete your account?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            deleteConfirmation();
        }
    }

    private void deleteConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ARE YOU REALLY SURE?");
        alert.setHeaderText(null);
        alert.setContentText("WARNING: ALL DATA WILL BE LOST");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                MainApplication.currentUser.deleteUser();
                MainApplication.setRoot("landingPage-view");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

