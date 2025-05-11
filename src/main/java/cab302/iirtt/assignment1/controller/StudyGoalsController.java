package cab302.iirtt.assignment1.controller;

import cab302.iirtt.assignment1.model.*;
import cab302.iirtt.assignment1.MainApplication;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import cab302.iirtt.assignment1.model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudyGoalsController {

    public Label usernameText;
    @FXML private VBox goalsContainer;
    @FXML private Pane createGoalPopup;

    @FXML private TextField goalTitleField;
    @FXML private TextArea goalDescriptionField;
    @FXML private DatePicker goalDueDatePicker;
    @FXML private RadioButton priorityHigh, priorityMedium, priorityLow;
    @FXML private CheckBox pinGoalCheckBox;

    private StudyGoalDAO studyGoalDAO = new StudyGoalDAO();
    private User currentUser;

    @FXML
    protected void switchToDashboard() throws IOException {
        MainApplication.setRoot("dashboard-view");
    }

    @FXML
    protected void switchToUploadMaterial() throws IOException {
        MainApplication.setRoot("uploadMaterial-view");
    }

    @FXML
    protected void switchToNotifications() throws IOException {
        MainApplication.setRoot("notifications-view");
    }

    @FXML
    protected void switchToAIChatbot() throws IOException {
        MainApplication.setRoot("aichatbot-view");
    }

    @FXML
    protected void switchToStudyGoals() throws IOException {
        MainApplication.setRoot("studyGoals-view");
    }

    @FXML
    protected void switchToMyFortune() throws IOException {
        MainApplication.setRoot("myFortune-view");
    }

    @FXML
    protected void switchToAdvice() throws IOException {
        MainApplication.setRoot("adviceTips-view");
    }

    @FXML
    protected void switchToSettings() throws IOException {
        MainApplication.setRoot("settings-view");
    }

    @FXML
    protected void logoutConfirm() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Logout");
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            MainApplication.currentUser = null;
            MainApplication.setRoot("landingPage-view");
        }
    }

    @FXML
    private AnchorPane displayPane;

    @FXML
    private void changeGoal() throws IOException {
        Text sampleText = new Text();
        sampleText.setText("hello, this is a sample text " + goaltype.getValue());

        sampleText.setX(50);
        sampleText.setY(50);

        displayPane.getChildren().add(sampleText);


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
    private ComboBox<String> goaltype;


    @FXML
    public void initialize() {
        currentUser = MainApplication.currentUser;
        // Dummy data for ComboBox
        goaltype.getItems().addAll("Option 1", "Option 2", "Option 3");

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
        setupHoverEffects();

        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }

        usernameText.setText(currentUser.getUsername());
        loadGoals();
        setupHoverEffects();
    }

    private void loadGoals() {
        goalsContainer.getChildren().clear();
        List<StudyGoal> goals = studyGoalDAO.getStudyGoalsByUserID(currentUser.getUserID());
        for (StudyGoal goal : goals) {
            goalsContainer.getChildren().add(createGoalCard(goal));
        }
    }

    private Pane createGoalCard(StudyGoal goal) {
        VBox card = new VBox(8);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-background-color:" + getPriorityColor(goal.getStudyGoalPriority()) + ";-fx-background-radius:15;");

        Label titleLabel = new Label(goal.getStudyGoalTitle());
        titleLabel.setStyle("-fx-font-weight:bold;-fx-font-size:16px;");
        Label descLabel = new Label(goal.getStudyGoalDescription());
        Label dueDateLabel = new Label("Due: " + goal.getDueDate());
        Label priorityLabel = new Label("Priority: " + goal.getStudyGoalPriority());

        card.getChildren().addAll(titleLabel, descLabel, dueDateLabel, priorityLabel);

        return card;
    }

    private String getPriorityColor(String priority) {
        return switch (priority) {
            case "High" -> "#F4511E";
            case "Medium" -> "#FBC02D";
            default -> "#64B5F6";
        };
    }

    @FXML
    private void openCreateGoalPopup() { createGoalPopup.setVisible(true); }

    @FXML
    private void createGoal() {
        if (goalTitleField.getText().isBlank() || goalDescriptionField.getText().isBlank() || goalDueDatePicker.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Please fill out all required fields.");
            return;
        }

        StudyGoal newGoal = new StudyGoal(
                goalTitleField.getText(),
                goalDescriptionField.getText(),
                priorityHigh.isSelected() ? "High" : priorityMedium.isSelected() ? "Medium" : "Low",
                false,
                pinGoalCheckBox.isSelected(),
                goalDueDatePicker.getValue().toString(),
                LocalDate.now().toString(),
                LocalDate.now().toString(),
                currentUser.getUserID()
        );

        studyGoalDAO.addStudyGoal(newGoal);
        goalsContainer.getChildren().add(createGoalCard(newGoal));
        clearForm();
        createGoalPopup.setVisible(false);
    }

    private void clearForm() {
        goalTitleField.clear();
        goalDescriptionField.clear();
        goalDueDatePicker.setValue(null);
        priorityLow.setSelected(true);
        pinGoalCheckBox.setSelected(false);
    }

    @FXML
    private void closeCreateGoalPopup() { createGoalPopup.setVisible(false); }

    private void showAlert(Alert.AlertType type, String content) {
        Alert alert = new Alert(type);
        alert.setContentText(content);
        alert.show();
    }

    private void setupHoverEffects() {
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
    }
}
