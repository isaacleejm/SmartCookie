package cab302.iirtt.assignment1.controller;

import cab302.iirtt.assignment1.MainApplication;
import cab302.iirtt.assignment1.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.animation.AnimationTimer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class SettingsController {

    public static UserDAO userDAO = new UserDAO();

    @FXML private Text usernameText;
    @FXML private Text goalsCompletedText;
    @FXML private Text streakProgressText;
    @FXML private Text responsesGatheredText;
    @FXML private Text nameDisplay;
    @FXML private Text emailDisplay;


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

        streakProgressText.setText(Integer.toString(MainApplication.currentUser.getStreak()));

        List<AIResponse> responsesGatheredList = aiResponseDAO.getAIResponsesByUserID(user.getUserID());
        int responsesGathered = responsesGatheredList.size();
        responsesGatheredText.setText(Integer.toString(responsesGathered));

        String username = user.getUsername();
        usernameText.setText(username);

        String fullname = user.getFirstName() + " " + user.getLastName();
        nameDisplay.setText(fullname);

        emailDisplay.setText(username);
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

    @FXML
    private void editNameOnClick() {
        User user = MainApplication.currentUser;
        // Create a custom dialog
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Edit Full Name");
        dialog.setHeaderText(null);

        // Add OK and Cancel buttons
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

        // Creates two TextFields and adds them to the dialog
        TextField firstName = new TextField();
        firstName.setPromptText("Enter text here...");
        firstName.setText(user.getFirstName());
        TextField lastName = new TextField();
        lastName.setPromptText("Enter text here...");
        lastName.setText(user.getLastName());

        // Layout the TextField with a label
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("First Name"), 0, 0);
        grid.add(new Label("Last Name"), 0, 1);
        grid.add(firstName, 1, 0);
        grid.add(lastName, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // Capture the result when OK is clicked
        dialog.setResultConverter(buttonType -> {
            if (buttonType == okButton) {
                MainApplication.currentUser.modifyUser(firstName.getText(),lastName.getText(),user.getUsername(),user.getPassword(),user.getMood(),user.getMemberSince());
                nameDisplay.setText(firstName.getText() + " " + lastName.getText());
            }
            return null; // Return null for Cancel
        });
        dialog.showAndWait().orElse(null);
    }
    @FXML
    private void editUsernameOnClick() {
        User user = MainApplication.currentUser;
        // Create a custom dialog
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Edit Username");
        dialog.setHeaderText(null);

        // Add OK and Cancel buttons
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

        // Create a TextField and add it to the dialog
        TextField username = new TextField();
        username.setPromptText("Enter text here...");
        username.setText(user.getUsername());

        // Layout the TextField with a label
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Username"), 0, 0);
        grid.add(username, 1, 0);

        dialog.getDialogPane().setContent(grid);

        // Capture the result when OK is clicked
        dialog.setResultConverter(buttonType -> {
            if (buttonType == okButton) {
                MainApplication.currentUser.modifyUser(user.getFirstName(),user.getLastName(),username.getText(),user.getPassword(),user.getMood(),user.getMemberSince());
                usernameText.setText(username.getText());
                emailDisplay.setText(username.getText());
            }
            return null; // Return null for Cancel
        });
        dialog.showAndWait().orElse(null);
    }

}
