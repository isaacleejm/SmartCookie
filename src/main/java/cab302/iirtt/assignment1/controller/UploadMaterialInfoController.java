package cab302.iirtt.assignment1.controller;

import cab302.iirtt.assignment1.MainApplication;
import cab302.iirtt.assignment1.model.IStudyMaterial;
import cab302.iirtt.assignment1.model.StudyMaterial;
import cab302.iirtt.assignment1.model.StudyMaterialDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;


public class UploadMaterialInfoController {

    public static StudyMaterialDAO studyMaterialDAO = new StudyMaterialDAO();

    @FXML
    Text pageTitleText;

    @FXML
    Text backButtonText;

    @FXML
    Text editButtonText;

    @FXML
    Rectangle editButton;

    @FXML
    TextField materialTitleField;

    @FXML
    TextField materialSubjectField;

    @FXML
    TextArea materialDescriptionField;

    @FXML
    TextField materialDateModifiedField;

    @FXML
    TextField materialDateCreatedField;

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

        StudyMaterial studyMaterial = studyMaterialDAO.getStudyMaterialByID(UploadMaterialController.selectedID);
        materialTitleField.setText(studyMaterial.getStudyMaterialTitle());
        materialSubjectField.setText(studyMaterial.getStudyMaterialSubject());
        materialDescriptionField.setText(studyMaterial.getStudyMaterialDescription());
        materialDateModifiedField.setText(studyMaterial.getDateModified());
        materialDateCreatedField.setText(studyMaterial.getDateCreated());

        materialDescriptionField.setWrapText(true);

        materialTitleField.setEditable(false);
        materialSubjectField.setEditable(false);
        materialDescriptionField.setEditable(false);
        materialDateModifiedField.setDisable(true);
        materialDateCreatedField.setDisable(true);

        materialTitleField.setStyle("-fx-background-color: #CCCCCC");
        materialSubjectField.setStyle("-fx-background-color: #CCCCCC");
        materialDescriptionField.setStyle("-fx-control-inner-background: #CCCCCC");
        materialDateModifiedField.setStyle("-fx-background-color: #CCCCCC");
        materialDateCreatedField.setStyle("-fx-background-color: #CCCCCC");
    }

    @FXML
    private void onEditButtonClick() {
        materialTitleField.setEditable(true);
        materialSubjectField.setEditable(true);
        materialDescriptionField.setEditable(true);
        backButtonText.setText("CANCEL");
        editButtonText.setText("CONFIRM");

        materialTitleField.setStyle("-fx-background-color: #FFFFFF");
        materialSubjectField.setStyle("-fx-background-color: #FFFFFF");
        materialDescriptionField.setStyle("-fx-control-inner-background: #FFFFFF");
        materialDateModifiedField.setStyle("-fx-background-color: #FFFFFF");
        materialDateCreatedField.setStyle("-fx-background-color: #FFFFFF");

        editButton.setOnMouseClicked(event -> modifyStudyMaterial());

    }

    private void modifyStudyMaterial() {
        String studyMaterialTitle = materialTitleField.getText();
        String studyMaterialSubject = materialSubjectField.getText();
        String studyMaterialDescription = materialDescriptionField.getText();
        String dateModified = LocalDate.now().toString();

        StudyMaterial updatedStudyMaterial = studyMaterialDAO.getStudyMaterialByID(UploadMaterialController.selectedID);

        updatedStudyMaterial.setStudyMaterialTitle(studyMaterialTitle);
        updatedStudyMaterial.setStudyMaterialSubject(studyMaterialSubject);
        updatedStudyMaterial.setStudyMaterialDescription(studyMaterialDescription);
        updatedStudyMaterial.setDateModified(dateModified);

        updatedStudyMaterial.modifyStudyMaterial(updatedStudyMaterial);

        try {
            MainApplication.setRoot("uploadMaterialInfo-view");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

