package cab302.iirtt.assignment1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #D8DBEB, #83A9E3);");

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setStyle("-fx-background-color: #F8F8F8; -fx-padding: 30; -fx-background-radius: 12;");
        centerBox.setPrefWidth(417);
        centerBox.setPrefHeight(597);

        Label title = new Label("Sign in");
        title.setFont(Font.font("Inter", FontWeight.BOLD, 32));

        ImageView logo = new ImageView(new Image("file:smartcookie.png"));
        logo.setFitWidth(209);
        logo.setFitHeight(174);

        Label emailLabel = new Label("Email");
        emailLabel.setFont(Font.font("Inter", 20));

        TextField emailField = new TextField();
        emailField.setPrefSize(377, 48);
        emailField.setStyle("-fx-background-color: #D9D9D9;");

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("Inter", 20));

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefSize(377, 48);
        passwordField.setStyle("-fx-background-color: #D9D9D9;");

        Button signInButton = new Button("Sign in");
        signInButton.setPrefSize(377, 48);
        signInButton.setStyle("-fx-background-color: #1935CA; -fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");

        Hyperlink forgotPassword = new Hyperlink("Forgot your password? Click here.");
        forgotPassword.setFont(Font.font("Inter", FontWeight.BOLD, 16));
        forgotPassword.setTextFill(Color.BLACK);

        Button createAccountButton = new Button("Create new account");
        createAccountButton.setPrefSize(377, 48);
        createAccountButton.setStyle("-fx-background-color: #1935CA; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");

        centerBox.getChildren().addAll(
                logo,
                title,
                emailLabel,
                emailField,
                passwordLabel,
                passwordField,
                signInButton,
                forgotPassword,
                createAccountButton
        );

        root.setCenter(centerBox);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setTitle("SmartCookie - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
