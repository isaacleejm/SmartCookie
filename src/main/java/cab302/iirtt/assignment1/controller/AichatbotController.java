package cab302.iirtt.assignment1.controller;

import cab302.iirtt.assignment1.MainApplication;
import cab302.iirtt.assignment1.model.AIResponse;
import cab302.iirtt.assignment1.model.AIResponseDAO;
import cab302.iirtt.assignment1.model.AdviceTip;
import cab302.iirtt.assignment1.model.GeminiAPI;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Insets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class AichatbotController extends AIResponse {

    @FXML private TextField inputField;
    @FXML private VBox chatContainer;
    @FXML private ScrollPane chatScrollPane;

    private final ArrayList<String> chatHistory = new ArrayList<>();

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

        // Ensure scrolling behavior is correct
        VBox.setVgrow(chatContainer, Priority.ALWAYS);
        chatContainer.setFillWidth(true);
        chatScrollPane.setFitToWidth(true);
        chatScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chatScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // Greeting: SmartestCookie
        Label nameLabel = new Label("SmartestCookie");
        HBox nameLabelBox = new HBox(nameLabel);
        nameLabelBox.setAlignment(Pos.CENTER_RIGHT);
        nameLabelBox.setPadding(new Insets(0, 20, 0, 20));

        Label greeting = new Label("Hello, how can I help you today?");
        greeting.setStyle("-fx-background-color: #e8e8e8; -fx-padding: 10; -fx-background-radius: 10;");
        greeting.setWrapText(true);
        greeting.setMaxWidth(500);

        HBox greetingBox = new HBox(greeting);
        greetingBox.setAlignment(Pos.CENTER_RIGHT);
        greetingBox.setPadding(new Insets(5, 10, 5, 10));

        // Add to chat container
        chatContainer.getChildren().addAll(nameLabelBox, greetingBox);

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

    public void testGenerateResponse(String userInput) {
        AdviceTip tip = new AdviceTip();
        tip.generateResponse(userInput);
    }

    @FXML
    private void handleSendMessage() {
        String userMessage = inputField.getText().trim();
        if (userMessage.isEmpty()) return;

        // --- USER name label
        Label userName = new Label("USER");
        HBox userNameBox = new HBox(userName);
        userNameBox.setAlignment(Pos.CENTER_LEFT);
        userNameBox.setPadding(new Insets(0, 10, 0, 10));

        // --- USER message
        Label userLabel = new Label(userMessage);
        userLabel.setStyle("-fx-background-color: #ffffff; -fx-padding: 10; -fx-background-radius: 10; -fx-border-color: #999;");
        userLabel.setWrapText(true);
        userLabel.setMaxWidth(500);

        HBox userBox = new HBox(userLabel);
        userBox.setAlignment(Pos.CENTER_LEFT);
        userBox.setPadding(new Insets(5, 10, 5, 10));

        chatContainer.getChildren().addAll(userNameBox, userBox);

        // --- Generate AI reply
        AdviceTip tip = new AdviceTip();
        tip.generateResponse(userMessage);

        String reply = tip.getChatHistory()
                .get(tip.getChatHistory().size() - 1)
                .replace("SmartestCookie: ", "");

        // --- SmartestCookie name label
        Label cookieName = new Label("SmartestCookie");
        HBox cookieNameBox = new HBox(cookieName);
        cookieNameBox.setAlignment(Pos.CENTER_RIGHT);
        cookieNameBox.setPadding(new Insets(0, 20, 0, 20));

        // --- SmartestCookie message
        Label cookieLabel = new Label(reply);
        cookieLabel.setStyle("-fx-background-color: #e8e8e8; -fx-padding: 10; -fx-background-radius: 10;");
        cookieLabel.setWrapText(true);
        cookieLabel.setMaxWidth(500);

        HBox cookieBox = new HBox(cookieLabel);
        cookieBox.setAlignment(Pos.CENTER_RIGHT);
        cookieBox.setPadding(new Insets(5, 10, 5, 10));

        chatContainer.getChildren().addAll(cookieNameBox, cookieBox);

        inputField.clear();
    }


}

