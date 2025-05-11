package cab302.iirtt.assignment1.model;

import cab302.iirtt.assignment1.MainApplication;

import java.time.LocalDate;

/**
 * Interface for the User Object with methods for the User class.
 */
public interface IUser {
    /**
     * Allows the User to sign in to an existing account
     * @param username The account's username
     * @param password The account's password
     * @return If match was found (successful login), return the User found; otherwise, return null.
     */
    public static User userLogin(String username, String password) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername(username);
        if (user == null) { return null; }
        if (password.equals(user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * Creates a new record of User and adds it into the User Table Database.
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @param username The account's username
     * @param password The account's password
     */
    public static void userRegistration(String firstName, String lastName, String username, String password) {
        UserDAO userDAO = new UserDAO();
        User user = new User(firstName, lastName, username, password, "neutral", LocalDate.now().toString(), LocalDate.now().toString(), 0);
        userDAO.addUser(user);
    }

    /**
     * Allows the User to Logout of an account. Function has zero parameters. The user's current session will end, and they will be directed to the login page.
     */
    public static void userLogout() {
        MainApplication.currentUser = null;
    }

    /**
     * Function deletes an existing record of a User from the User Database
     * All records related to this User (including Fortune, StudyMaterial, StudyGoal) will be deleted along with the User.
     * Once all data has been deleted, they will be directed to the login page.
     * @return
     */
    abstract boolean deleteUser();


    /**
     * Modify/Updates the User's information, such as: (name. password, phoneNumber, etc.).
     * Takes in multiple parameters.
     * If the all parameters are in the correct formatting, the user's data will be updated with the new data and a "successful pop up will display";
     * otherwise, it will display an error message for that error.
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param mood
     * @param memberSince
     * @return
     */
    abstract boolean modifyUser(String firstName, String lastName, String username, String password, String mood, String memberSince);

}
 