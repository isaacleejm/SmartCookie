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
    @FXML private ComboBox<String> statusFilterBox;

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


    /**
     * Hover for Left Navigation Bar.
     */
    @FXML
    private Rectangle dashboard;
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

    /**
     * Initializes the controller. Loads the current user, goals, and UI settings.
     */
    @FXML
    public void initialize() {
        currentUser = MainApplication.currentUser;

        /**
         * Hover effect
         */
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
        statusFilterBox.getItems().addAll("All", "In Progress", "Completed");
        statusFilterBox.setValue("All");
        statusFilterBox.setOnAction(e -> loadGoals());
        loadGoals();
        setupHoverEffects();
    }

    /**
     * Loads and displays all study goals filtered and sorted according to user input.
     */
    private void loadGoals() {
        goalsContainer.getChildren().clear();
        List<StudyGoal> goals = studyGoalDAO.getStudyGoalsByUserID(currentUser.getUserID());

        String selectedStatus = statusFilterBox.getValue();
        goals = goals.stream()
                .filter(g -> selectedStatus.equals("All") ||
                        (selectedStatus.equals("Completed") && g.getStudyGoalStatus()) ||
                        (selectedStatus.equals("In Progress") && !g.getStudyGoalStatus()))
                .sorted((g1, g2) -> {
                    if (g1.getStudyGoalPinned() != g2.getStudyGoalPinned())
                        return Boolean.compare(g2.getStudyGoalPinned(), g1.getStudyGoalPinned());
                    return Integer.compare(priorityValue(g2.getStudyGoalPriority()), priorityValue(g1.getStudyGoalPriority()));
                })
                .toList();

        for (StudyGoal goal : goals) {
            goalsContainer.getChildren().add(createGoalCard(goal));
        }
    }

    /**
     * Returns an integer representing the priority level.
     * @param priority The priority as a string.
     * @return Integer value of the priority.
     */
    private int priorityValue(String priority) {
        return switch (priority) {
            case "High" -> 3;
            case "Medium" -> 2;
            default -> 1;
        };
    }

    /**
     * Creates and returns a UI card element for the specified study goal.
     * @param goal The study goal object.
     * @return A Pane representing the goal card.
     */
    private Pane createGoalCard(StudyGoal goal) {
        VBox card = new VBox(8);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-background-color:" + getPriorityColor(goal.getStudyGoalPriority()) + ";-fx-background-radius:15;");

        Label titleLabel = new Label(goal.getStudyGoalTitle());
        titleLabel.setStyle("-fx-font-weight:bold;-fx-font-size:16px;");
        Label descLabel = new Label(goal.getStudyGoalDescription());
        Label dueDateLabel = new Label("Due: " + goal.getDueDate());
        Label priorityLabel = new Label("Priority: " + goal.getStudyGoalPriority());
        Label statusLabel = new Label(goal.getStudyGoalStatus() ? "Completed" : "In Progress");

        HBox actions = new HBox(10);
        Button deleteBtn = new Button("🗑 Delete Goal");
        deleteBtn.setOnAction(e -> {
            studyGoalDAO.deleteStudyGoal(goal);
            loadGoals();
        });

        Button pinBtn = new Button(goal.getStudyGoalPinned() ? "📌 Unpin Goal" : "📍 Pin Goal");
        pinBtn.setOnAction(e -> {
            goal.setStudyGoalPinned(!goal.getStudyGoalPinned());
            studyGoalDAO.updateStudyGoal(goal);
            loadGoals();
        });

        Button editBtn = new Button("✏ Edit Goal");
        editBtn.setOnAction(e -> openEditGoalPopup(goal));

        Button statusToggleBtn = new Button(goal.getStudyGoalStatus() ? "↩ Mark In Progress" : "✔ Mark Completed");
        statusToggleBtn.setOnAction(e -> {
            goal.setStudyGoalStatus(!goal.getStudyGoalStatus());
            studyGoalDAO.updateStudyGoal(goal);
            loadGoals();
        });

        actions.getChildren().addAll(deleteBtn, pinBtn, editBtn, statusToggleBtn);

        card.getChildren().addAll(titleLabel, descLabel, dueDateLabel, priorityLabel, statusLabel, actions);
        return card;
    }

    private StudyGoal currentEditingGoal;

    /**
     * Returns the color code associated with a given priority level.
     * @param priority The priority level.
     * @return A hex color code as a string.
     */
    private String getPriorityColor(String priority) {
        return switch (priority) {
            case "High" -> "#F4511E";
            case "Medium" -> "#FBC02D";
            default -> "#64B5F6";
        };
    }

    /**
     * Opens the popup for editing a study goal.
     * @param goal The goal to edit.
     */
    @FXML
    private void openEditGoalPopup(StudyGoal goal) {
        currentEditingGoal = goal;
        goalTitleField.setText(goal.getStudyGoalTitle());
        goalDescriptionField.setText(goal.getStudyGoalDescription());
        goalDueDatePicker.setValue(LocalDate.parse(goal.getDueDate()));

        switch (goal.getStudyGoalPriority()) {
            case "High": priorityHigh.setSelected(true); break;
            case "Medium": priorityMedium.setSelected(true); break;
            default: priorityLow.setSelected(true); break;
        }

        pinGoalCheckBox.setSelected(goal.getStudyGoalPinned());

        createGoalPopup.setVisible(true);
    }

    /**
     * Opens the popup for creating a new study goal.
     */
    @FXML
    private void openCreateGoalPopup() {
        currentEditingGoal = null;
        clearForm();
        createGoalPopup.setVisible(true);
    }

    /**
     * Creates or updates a study goal based on the form inputs.
     */
    @FXML
    private void createGoal() {
        if (goalTitleField.getText().isBlank() || goalDescriptionField.getText().isBlank() || goalDueDatePicker.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Please fill out all required fields.");
            return;
        }

        if (currentEditingGoal != null) {
            currentEditingGoal.setStudyGoalTitle(goalTitleField.getText());
            currentEditingGoal.setStudyGoalDescription(goalDescriptionField.getText());
            currentEditingGoal.setDueDate(goalDueDatePicker.getValue().toString());
            currentEditingGoal.setStudyGoalPriority(priorityHigh.isSelected() ? "High" : priorityMedium.isSelected() ? "Medium" : "Low");
            currentEditingGoal.setStudyGoalPinned(pinGoalCheckBox.isSelected());

            studyGoalDAO.updateStudyGoal(currentEditingGoal);
            currentEditingGoal = null;
        } else {
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
        }

        clearForm();
        createGoalPopup.setVisible(false);
        loadGoals();
    }

    /**
     * Clears the goal form fields.
     */
    private void clearForm() {
        goalTitleField.clear();
        goalDescriptionField.clear();
        goalDueDatePicker.setValue(null);
        priorityLow.setSelected(true);
        pinGoalCheckBox.setSelected(false);
    }

    /**
     * Closes the goal creation/edit popup.
     */
    @FXML
    private void closeCreateGoalPopup() { createGoalPopup.setVisible(false); }

    /**
     * Displays an alert dialog with the specified message.
     * @param type The type of alert.
     * @param content The message content.
     */
    private void showAlert(Alert.AlertType type, String content) {
        Alert alert = new Alert(type);
        alert.setContentText(content);
        alert.show();
    }

    /**
     * Sets up hover effects for the navigation bar items.
     */
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
