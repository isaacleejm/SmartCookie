package cab302.iirtt.assignment1.controller;

import cab302.iirtt.assignment1.MainApplication;
import cab302.iirtt.assignment1.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.animation.AnimationTimer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class DashboardController {

    protected static int sessionDuration;
    private long startTime;
    private AnimationTimer timer;

    @FXML private Text usernameText;
    @FXML private Text goalsCompletedText;
    @FXML private Text sessionDurationText;
    @FXML private Text responsesGatheredText;
    @FXML private Text todayFortuneText;
    @FXML private Text funPredictionText;

    // Streak Indicator
    @FXML private Rectangle day1;
    @FXML private Rectangle day2;
    @FXML private Rectangle day3;
    @FXML private Rectangle day4;
    @FXML private Rectangle day5;
    @FXML private Rectangle day6;
    @FXML private Rectangle day7;

    // Streak Text
    @FXML private Text day1Text;
    @FXML private Text day2Text;
    @FXML private Text day3Text;
    @FXML private Text day4Text;
    @FXML private Text day5Text;
    @FXML private Text day6Text;
    @FXML private Text day7Text;

    // Today's Fortune ScrollPane
    private int value = 0;
    @FXML private ScrollPane scrollPane;
    @FXML private AnchorPane scrollContent;


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

        String username = user.getUsername();
        usernameText.setText(username);

        List<StudyGoal> goalsCompletedList = studyGoalDAO.getCompletedStudyGoalsByUserID(user.getUserID());
        int goalsCompleted = goalsCompletedList.size();
        goalsCompletedText.setText(Integer.toString(goalsCompleted));

        List<AIResponse> responsesGatheredList = aiResponseDAO.getAIResponsesByUserID(user.getUserID());
        int responsesGathered = responsesGatheredList.size();
        responsesGatheredText.setText(Integer.toString(responsesGathered));

        String todayFortune = "Not Yet Generated";
        FortuneCookie fortuneCookie = new FortuneCookie();
        fortuneCookie.generateResponse("");
        AIResponse fortuneCookieResponse = aiResponseDAO.getTodayFortune(MainApplication.currentUser.getUserID());
        todayFortune = fortuneCookieResponse.getResponseText();
        todayFortuneText.setText(todayFortune);

        String prediction = "Not Yet Generated";
        FunPrediction funPrediction = new FunPrediction();
        List<AIResponse> funPredictionResponses = aiResponseDAO.getAIResponsesByType(MainApplication.currentUser.getUserID(), AIResponse.ResponseType.FUN_PREDICTION);
        funPrediction.generateResponse("");
        List<AIResponse>  newFunPredictionResponses = aiResponseDAO.getAIResponsesByType(MainApplication.currentUser.getUserID(), AIResponse.ResponseType.FUN_PREDICTION);
        for (int i = 0; i < funPredictionResponses.size(); i++) {
            newFunPredictionResponses.removeFirst();
        }
        System.out.println(newFunPredictionResponses.size());
        prediction = newFunPredictionResponses.getFirst().getResponseText();
        funPredictionText.setText(prediction);



        startTimer(); // starts the session timer
        // TODO: This method is currently resetting every time dashboard runs

        int streak = user.getStreak();
        int streakWeek = ((streak - 1) / 7);

        day1Text.setText("Day " + (streakWeek * 7 + 1));
        day2Text.setText("Day " + (streakWeek * 7 + 2));
        day3Text.setText("Day " + (streakWeek * 7 + 3));
        day4Text.setText("Day " + (streakWeek * 7 + 4));
        day5Text.setText("Day " + (streakWeek * 7 + 5));
        day6Text.setText("Day " + (streakWeek * 7 + 6));
        day7Text.setText("Day " + (streakWeek * 7 + 7));

        int streakDisplay = streak - (streakWeek * 7);

        switch (streakDisplay) {
            case 7:
                day7.setFill(Paint.valueOf("#55FF00"));
            case 6:
                day6.setFill(Paint.valueOf("#55FF00"));
            case 5:
                day5.setFill(Paint.valueOf("#55FF00"));
            case 4:
                day4.setFill(Paint.valueOf("#55FF00"));
            case 3:
                day3.setFill(Paint.valueOf("#55FF00"));
            case 2:
                day2.setFill(Paint.valueOf("#55FF00"));
            case 1:
                day1.setFill(Paint.valueOf("#55FF00"));
                break;
            default:
                break;
        }

        // Displaying Today's Fortune
        int studyGoalID;
        String studyGoalTitle;
        String studyGoalStatus;
        List<StudyGoal> studyGoalList = studyGoalDAO.getStudyGoalsByUserID(MainApplication.currentUser.getUserID());
        while(value < studyGoalList.size()) {
            if (studyGoalList.get(value).getDueDate().equals(LocalDate.now().toString())) {
                studyGoalID = studyGoalList.get(value).getStudyGoalID();
                studyGoalTitle = "Title : " + studyGoalList.get(value).getStudyGoalTitle();
                studyGoalStatus = "Status: " + studyGoalList.get(value).getStudyGoalStatus();
                try {
                    addToScroll(studyGoalID, studyGoalTitle, studyGoalStatus);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (value == 0) {
            Rectangle rectangle = new Rectangle(650, 145, Color.web("#d7d7d7"));
            rectangle.setArcHeight(7);
            rectangle.setArcWidth(42);

            Text text = new Text("You have No Goals Today");
            text.setFont(new Font("System", 28));
            text.setX(170);
            text.setY(75);

            scrollContent.getChildren().add(rectangle);
            scrollContent.getChildren().add(text);
        }
        if (scrollContent.getPrefHeight()<=145){
            scrollContent.setPrefHeight(145);
        } else {
            scrollContent.setPrefHeight(scrollContent.getPrefHeight() + 47);
        }

    }

    private void addToScroll(int studyGoalID, String studyGoalTitle, String studyGoalStatus) throws IOException {
        int scrollPaneWidth = 625;
        int spacing = 5;
        int blockWidth = scrollPaneWidth - (2*spacing) ;
        int blockHeight = 47;
        double borderRadius = blockHeight - 40;
        int rectangleHeight = blockHeight - 5;

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setMaxWidth(blockWidth);
        anchorPane.setMaxHeight(blockHeight);
        anchorPane.setLayoutX(spacing);
        anchorPane.setLayoutY((value* blockHeight)+spacing);
        anchorPane.setId(Integer.toString(value));

        Rectangle rectangle = new Rectangle(blockWidth, rectangleHeight, Color.web("#d7d7d7"));
        scrollContent.setPrefHeight((value *blockHeight) + spacing) ;
        rectangle.setArcHeight(borderRadius);
        rectangle.setArcWidth(borderRadius);

        Font newFont = new Font("System", 15);

        Text textUser = new Text(studyGoalTitle);
        textUser.setLayoutY((rectangleHeight/2)+5);
        textUser.setFont(newFont);
        textUser.setLayoutX(100);
        textUser.setWrappingWidth(350);

        Text textRating = new Text(studyGoalStatus);
        textRating.setLayoutY((rectangleHeight/2)+5);
        textRating.setFont(newFont);
        textRating.setLayoutX(450);
        textRating.setWrappingWidth(350);

        anchorPane.getChildren().add(rectangle);
        anchorPane.getChildren().add(textUser);
        anchorPane.getChildren().add(textRating);

        scrollContent.getChildren().add(anchorPane);

        value ++;
    }

    private void startTimer() {
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
}

