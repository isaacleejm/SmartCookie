package cab302.iirtt.assignment1.controller;

import cab302.iirtt.assignment1.MainApplication;
import cab302.iirtt.assignment1.model.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class MyFortuneController {


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

    private AIResponseDAO aiResponseDAO = new AIResponseDAO();
    private int count = 1;
    private int colorIndex = 0;

    @FXML
    private VBox scrollContent;

    @FXML
    private ScrollPane scrollPane;


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
        // Dummy data for ComboBox

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

        loadResponses();
    }

    private Pane createGoalCard(AIResponse response) {
        String color;

        switch (colorIndex) {
            case 0:
                color = "#F4511E";
                colorIndex = 1;
                break;
            case 1:
                color = "#FBC02D";
                colorIndex = 2;
                break;
            default:
                color = "#64B5F6";
                colorIndex = 0;
                break;
        }
        VBox card = new VBox(8);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-background-color:" + color + ";-fx-background-radius:15;");

        Label numberLabel = new Label(count +".");
        numberLabel.setStyle("-fx-font-weight:bold;-fx-font-size:16px;");
        Label descriptionLabel = new Label(response.getResponseText());
        Label dateCreatedLabel = new Label("Date Generated: " + response.getResponseDate());
        Label ratingLabel = new Label(response.getResponseRating() > 0 ? "Rating: " + Integer.toString(response.getResponseRating()) : "Rating: Unrated");
        Label statusLabel = new Label(response.getFavourite() ? "Favourited" : "Not Favourited");

        HBox actions = new HBox(10);
        Button deleteBtn = new Button("🗑 Delete Response");
        deleteBtn.setOnAction(e -> {
            aiResponseDAO.deleteAIResponse(response);
            loadResponses();
        });

        Button statusToggleBtn = new Button(response.getFavourite() ? "↩ Unfavourite" : "✔ Mark as Favourite");
        statusToggleBtn.setOnAction(e -> {
            response.setFavourite(!response.getFavourite());
            aiResponseDAO.updateAIResponse(response);
            loadResponses();
        });

        actions.getChildren().addAll(deleteBtn, statusToggleBtn);

        card.getChildren().addAll(numberLabel, descriptionLabel, dateCreatedLabel, ratingLabel, statusLabel, actions);
        count++;
        return card;
    }

    /**
     * Loads and displays all fortune AI Responses for current user.
     */
    private void loadResponses() {
        scrollContent.getChildren().clear();
        count = 1;
        colorIndex = 0;
        List<AIResponse> responses = aiResponseDAO.getAIResponsesByType(MainApplication.currentUser.getUserID(), AIResponse.ResponseType.FUN_PREDICTION);
        System.out.println("size:" +responses.size());
        for (AIResponse response : responses) {
            System.out.println(count);
            scrollContent.getChildren().add(createGoalCard(response));
        }
    }
}

