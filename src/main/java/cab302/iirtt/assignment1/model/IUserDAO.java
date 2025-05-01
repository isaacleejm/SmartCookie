package cab302.iirtt.assignment1.model;


import java.util.List;

/**
 * Interface for the User Data Access Object that handles
 * the CRUD operations for the User class with the database.
 * **/
public interface IUserDAO {
    /**
     * Adds a new user to the database.
     * @param user The user to add.
     */
    abstract void addUser(User user);
    /**
     * Updates an existing user in the database.
     * @param user The user to update.
     */
    abstract void updateUser(User user);
    /**
     * Deletes a user from the database.
     * @param user The user to delete.
     */
    abstract void deleteUserByID(User user);
    /**
     * Retrieves a user from the database.
     * @param userID The id of the user to retrieve.
     * @return The user with the given id, or null if not found.
     */
    abstract User getUserByID(int userID);
    /**
     * Retrieves a user from the database.
     * @param username The email of the user to retrieve.
     * @return The user with the given email, or null if not found.
     */
    abstract User getUserByUsername(String username);
    /**
     * Retrieves all users from the database.
     * @return A list of all users in the database.
     */
    abstract List<User> getAllUsers();
}
