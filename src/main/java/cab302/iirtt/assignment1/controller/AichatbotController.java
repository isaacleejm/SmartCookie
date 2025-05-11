package cab302.iirtt.assignment1.controller;

import cab302.iirtt.assignment1.MainApplication;
import cab302.iirtt.assignment1.model.AIResponse;
import cab302.iirtt.assignment1.model.AIResponseDAO;
import cab302.iirtt.assignment1.model.GeminiAPI;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


public class AichatbotController extends AIResponse {

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
    }



    /**
     * Generating an AI response based on userInput into the text box
     * @param userInput The prompt for the AI.
     */
    @Override
    public void generateResponse(String userInput) {
        if (MainApplication.currentUser == null) {
            System.out.println("Error, User is not Logged-in to an account");
            return;
        }
        // Add current user question to history (before asking Gemini)
        chatHistory.add("User: " + userInput);

        // Build chat history into a single prompt
        StringBuilder promptBuilder = new StringBuilder();
        for (String entry : chatHistory) {
            promptBuilder.append(entry).append("\n");
        }
        promptBuilder.append("SmartestCookie: Please generate this response in less than 150 words.");

        // Call Gemini with full history
        String reply = new GeminiAPI().run(promptBuilder.toString());

        // Add AI's response to history
        chatHistory.add("SmartestCookie: " + reply);

        AIResponse adviceTipResponse = new AIResponse (
                AIResponse.ResponseType.ADVICE_TIP,
                0,
                LocalDate.now().toString(),
                reply,
                userInput,
                false,
                MainApplication.currentUser.getUserID()
        );

        AIResponseDAO dao = new AIResponseDAO();
        dao.addAIResponse(adviceTipResponse);

        // Print input
        System.out.println("Input: " + userInput + "\n");

        // Print reply
        System.out.println("Reply: " + reply);
    }


}

