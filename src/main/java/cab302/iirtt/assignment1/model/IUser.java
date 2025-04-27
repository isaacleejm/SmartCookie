package cab302.iirtt.assignment1.model;

public interface IUser {
    // Allows the User to sign in to an existing account, using an email and password
    // This method will have two parameters (String email, String Password)
    // and compare them with very record of Users in the database until a match is found.
    // If a match is found, the user is brought to the Dashboard Page, and it will be logged in as the matched user;
    // Otherwise, display error message on the Login Page.
    abstract void userLogin(String email, String password);

    // Creates a new record of User that will be added to the User database
    // This method will take in multiple parameters required for user login such as: (name, email, password, confirmPassword, phoneNumber, etc).
    // Each parameter will have to be tested for correct formatting, such as password has to be n length, and passwords have to be same, or phone number is invalid.
    // If an incorrect format is used, an error message will display for that error, otherwise, the account will be created successfully,
    // the User object will be added to the User database, and they will be directed to the login page.
    abstract void userRegistration(String name, String password, String confirmPassword, String phoneNumber);

    // Allows the User to Logout of an account
    // This method will have zero parameters ()
    // The user's current session will end, and they will be directed to the login page.
    abstract void userLogout();

    // Deletes an existing record of a User from the User Database
    // All records related to this User (including Fortune, StudyMaterial, StudyGoal) will be deleted along with the User.
    // Once all data has been deleted, they will be directed to the login page.
    abstract boolean deleteUser();

    // Modify/Updates the User's information, such as: (name. password, phoneNumber, etc.)
    // Takes in multiple parameters
    // If the all parameters are in the correct formatting, the user's data will be updated with the new data and a "successful pop up will display";
    // otherwise, it will display an error message for that error.
    abstract boolean modifyUser();

}
