package cab302.iirtt.assignment1;

import cab302.iirtt.assignment1.model.IUser;
import cab302.iirtt.assignment1.model.User;
import cab302.iirtt.assignment1.model.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;

    @FXML
    private void initialize() {
        // No-op until listeners or validation hooks needed
    }

    @FXML
    private boolean setFirstNameField() {
        String firstName = firstNameField.getText().trim();
        // Format checking for Username
        for (int i = 0; i < firstName.length(); i++) {
            char character = firstName.charAt(i);
            if (!(Character.isLetter(character) || Character.isWhitespace(character))) {
                System.out.println("First Name can only contain letters");
                return false;
            }
        }
        System.out.println("Valid First Name used");
        return true;
    }

    @FXML
    private boolean setLastNameField() {
        String lastName = lastNameField.getText().trim();
        // Format checking for Username
        for (int i = 0; i < lastName.length(); i++) {
            char character = lastName.charAt(i);
            if (!(Character.isLetter(character) || Character.isWhitespace(character))) {
                System.out.println("Last Name can only contain letters");
                return false;
            }
        }
        System.out.println("Valid Last Name used");
        return true;
    }

    @FXML
    private boolean setUsernameField() {
        String username = usernameField.getText().trim();
        // Format checking for Username
        if (username.length() < 4) {
            System.out.println("Username must have more than 3 characters");
            return false;
        }
        for (int i = 0; i < username.length(); i++) {
            char character = username.charAt(i);
            if (Character.isSpaceChar(character)) {
                System.out.println("Username cannot have spaces");
                return false;
            }
            if (!(Character.isLetter(character) || Character.isDigit(character))) {
                System.out.println("username can only contain letter and digits");
                return false;
            }
        }
        // Once Format has been checked, it checks if username exist in the database
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername(usernameField.getText().trim());
        if (userDAO.getUserByUsername(usernameField.getText().trim()) == null) {
            System.out.println("username available");// Replace this line with the message/Text display, "Username is available"
            return true;
        } else {
            System.out.println("username taken");// Replace this line with the message/Text display, "Username has been taken"
            return false;
        }
    }

    @FXML
    private boolean setPasswordField() {
        String password = passwordField.getText();
        if (!password.isEmpty()) {
            if (password.length() > 7) {
                boolean hasUpper = false;
                boolean hasLower = false;
                boolean hasDigit = false;
                boolean hasSpaces = false;
                boolean hasSpecial = false;
                for (int i = 0; i < password.length(); i++) {
                    char character = password.charAt(i);
                    if (Character.isLowerCase(character)) {
                        hasUpper = true;
                    }
                    if (Character.isUpperCase(character)) {
                        hasLower = true;
                    }
                    if (Character.isDigit(character)) {
                        hasDigit = true;
                    }
                    if (Character.isSpaceChar(character)) {
                        hasSpaces = true;
                    }
                    if (!Character.isLetter(character) && !Character.isDigit(character) && !Character.isWhitespace(character)) {
                        hasSpecial = true;
                    }
                }
                if (!hasSpaces) {
                    if (hasUpper && hasLower) {
                        if (hasDigit) {
                            if (hasSpecial) {
                                System.out.println("ALL CONDITIONS OF THE PASSWORD HAVE BEEN MET"); // ONCE ALL CONDITIONS WAS MET AND THE PASSWORD IS VALID
                                return true;
                            } else {
                                System.out.println("Password must have at least one Special Characters");
                            }
                        } else {
                            System.out.println("Password must have at least one number");
                        }
                    } else {
                        System.out.println("Password must have both Upper and Lower Cases");
                    }
                } else {
                    System.out.println("Password cannot have spaces");
                }
            } else {
                System.out.println("Password must be more than 7");
            }
        } else {
            System.out.println("Password cannot be empty!");
        }
        return false;
    }

    @FXML
    private boolean setConfirmPasswordField() {
        if (passwordField.getText().equals(confirmPasswordField.getText())) {
            System.out.println("Passwords match");
            return true;
        } else {
            System.out.println("Passwords do not match");
        }
        return false;
    }

    @FXML
    private void onSignUpButtonClick() {
        // TODO: Add username format validation, password rules, and database integration
        // Checks if all fields are of correct format
        if (setFirstNameField() && setLastNameField() && setUsernameField() && setPasswordField() && setConfirmPasswordField()) {
            // Creates a new user account and stores it within the database
            IUser.userRegistration(firstNameField.getText().trim(), lastNameField.getText().trim(), usernameField.getText().trim(), passwordField.getText());
            // Placeholder success
            showAlert(Alert.AlertType.INFORMATION, "Account created successfully!");
            // TODO: Navigate to login screen after signup
        }




    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait();
    }
}