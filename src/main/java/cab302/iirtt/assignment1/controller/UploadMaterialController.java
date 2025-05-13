package cab302.iirtt.assignment1.controller;

import cab302.iirtt.assignment1.MainApplication;
import cab302.iirtt.assignment1.model.IStudyMaterial;
import cab302.iirtt.assignment1.model.StudyMaterial;
import cab302.iirtt.assignment1.model.StudyMaterialDAO;
import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UploadMaterialController {

    private int value = 0;

    private int studyMaterialID;
    private String studyMaterialTitle;
    private String studyMaterialSubject;
    private String buttonID;
    public static int selectedID;

    @FXML
    public static double scrollValue = 0;

    @FXML
    private AnchorPane scrollContent;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button uploadButton;

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

    @FXML
    private void switchToMaterialCreate() throws IOException {
        MainApplication.setRoot("UploadMaterialCreate-view");
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

    @FXML
    private void confirmDeletePage() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Detete");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this Study Material?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            IStudyMaterial.deleteStudyMaterial(selectedID);
            MainApplication.setRoot("uploadMaterial-view");
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

        // Displaying Study Material of the current User
        List<StudyMaterial> studyMaterialList = IStudyMaterial.getStudyMaterials();
        while(value < studyMaterialList.size()) {
            studyMaterialID = studyMaterialList.get(value).getStudyMaterialID();
            studyMaterialTitle = "Title : " + studyMaterialList.get(value).getStudyMaterialTitle();
            studyMaterialSubject = "Subject : " + studyMaterialList.get(value).getStudyMaterialSubject();
            buttonID = Integer.toString(value);
            try {
                addToScroll(studyMaterialID, studyMaterialTitle, studyMaterialSubject, buttonID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        scrollPane.setVvalue(scrollValue);


        if (scrollContent.getPrefHeight()<495){
            scrollContent.setPrefHeight(495);
        } else {
            scrollContent.setPrefHeight(scrollContent.getPrefHeight()+ 100);
        }
    }

    @FXML
    private void infoButtonF(int studyMaterialID) throws IOException {
        // TODO: Link StudyMaterial ID to the INFO BUTTON, go to studyMaterial-view (where detailed information of studyMaterial is displayed.

        scrollValue = scrollPane.getVvalue();
        selectedID = studyMaterialID;
        MainApplication.setRoot("uploadMaterialInfo-view");

    }

    @FXML
    private void addToScroll(int studyMaterialID, String studyMaterialTitle, String studyMaterialSubject, String buttonID) throws IOException {

        int scrollPaneWidth = 975;
        int spacing = 20;
        int blockWidth = scrollPaneWidth - (2*spacing) ;
        int blockHeight = 100;
        double borderRadius = blockHeight -10;
        int rectangleHeight = blockHeight-10;
        int imageSize = blockHeight-35;

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

        Text textUser = new Text(studyMaterialTitle);
        textUser.setLayoutY((rectangleHeight/2)+5);
        textUser.setFont(newFont);
        textUser.setLayoutX(100);
        textUser.setWrappingWidth(350);

        Text textRating = new Text(studyMaterialSubject);
        textRating.setLayoutY((rectangleHeight/2)+5);
        textRating.setFont(newFont);
        textRating.setLayoutX(450);
        textRating.setWrappingWidth(350);

        StackPane infoStack = new StackPane();
        infoStack.setLayoutX((blockWidth-imageSize-12.5));
        infoStack.setLayoutY((rectangleHeight-imageSize )/2);
        infoStack.setPrefWidth(imageSize);
        infoStack.setPrefHeight(imageSize);

        StackPane trashStack = new StackPane();
        trashStack.setLayoutX((blockWidth-(2*imageSize)-22.5));
        trashStack.setLayoutY((rectangleHeight-imageSize )/2);
        trashStack.setPrefWidth(imageSize);
        trashStack.setPrefHeight(imageSize);



        Button infoButton = new Button();
        infoButton.setPrefWidth(imageSize);
        infoButton.setPrefHeight(imageSize);
        infoButton.setId(Integer.toString(studyMaterialID));
        infoButton.setStyle("-fx-background-radius: 40");
        infoButton.setOnAction( e -> {try {
            infoButtonF(Integer.parseInt(infoButton.getId()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }});
        infoButton.setOpacity(0);
        infoButton.setCursor(Cursor.HAND);
        infoButton.setPickOnBounds(false);


        Button trashButton = new Button();
        trashButton.setPrefWidth(imageSize);
        trashButton.setPrefHeight(imageSize);
        trashButton.setId(Integer.toString(studyMaterialID));
        trashButton.setStyle("-fx-background-radius: 40");
        trashButton.setOnAction( e -> {try {
            selectedID = Integer.parseInt(trashButton.getId());
            confirmDeletePage();
        } catch (IOException e2) {
            e2.printStackTrace();
        }});
        trashButton.setOpacity(0);
        trashButton.setCursor(Cursor.HAND);
        trashButton.setPickOnBounds(false);

        Image infoIcon = new Image(MainApplication.class.getResource("images/infoIcon.png").toString());
        ImageView infoIconView = new ImageView(infoIcon);
        infoIconView.setFitHeight(imageSize);
        infoIconView.setFitWidth(imageSize);

        Image trashIcon = new Image(MainApplication.class.getResource("images/trashIcon.png").toString());
        ImageView trashIconView = new ImageView(trashIcon);
        trashIconView.setFitHeight(imageSize);
        trashIconView.setFitWidth(imageSize);

        Image userIcon = new Image(MainApplication.class.getResource("images/userIcon.png").toString());
        ImageView userIconView = new ImageView(userIcon);
        userIconView.setFitWidth(imageSize);
        userIconView.setFitHeight(imageSize);
        userIconView.setLayoutX((rectangleHeight-imageSize)/2);
        userIconView.setLayoutY((rectangleHeight-imageSize )/2);


        Line newLine = new Line();
        newLine.setStartY(0);
        newLine.setEndY(imageSize);
        newLine.setStroke(Color.web("#a0a0a0"));
        newLine.setStrokeWidth(2);
        newLine.setLayoutY((rectangleHeight-imageSize)/2);
        newLine.setLayoutX(435);


        infoStack.getChildren().add(infoIconView);
        infoStack.getChildren().add(infoButton);

        trashStack.getChildren().add(trashIconView);
        trashStack.getChildren().add(trashButton);


        anchorPane.getChildren().add(rectangle);
        anchorPane.getChildren().add(userIconView);
        anchorPane.getChildren().add(textUser);
        anchorPane.getChildren().add(newLine);
        anchorPane.getChildren().add(textRating);
        anchorPane.getChildren().add(infoStack);
        anchorPane.getChildren().add(trashStack);

        scrollContent.getChildren().add(anchorPane);

        value ++;
    }
}
