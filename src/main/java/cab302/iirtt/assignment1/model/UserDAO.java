package cab302.iirtt.assignment1.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private Connection connection;

    // Constructor, creates connection with database if it exists, otherwise, it will create a database.db. Then it will create a users table if it does not already exist.
    public UserDAO() {
        connection = DatabaseConnection.getInstance();
        createTable();
        // Used for testing, TO BE REMOVED LATER
        insertSampleData();
    }

    // Creates the users table if it does not exist yet
    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS users ("
                    + "userID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "email VARCHAR NOT NULL,"
                    + "password VARCHAR NOT NULL,"
                    + "mood VARCHAR NOT NULL,"
                    + "memberSince DATETIME NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // This method inserts sample data into users table and will be removed when submitting. NOTE: This method does not consider duplicate data
    private void insertSampleData() {
        try {
            // Clear before inserting
            Statement clearStatement = connection.createStatement();
            String clearQuery = "DELETE FROM users";
            clearStatement.execute(clearQuery);
            Statement insertStatement = connection.createStatement();
            String insertQuery = "INSERT INTO users (firstName, lastName, email, password, mood, memberSince) VALUES "
                    + "('John', 'Doe', 'johndoe@example.com', 'password123', 'neutral', '" + LocalDate.now() + "'),"
                    + "('Jane', 'Doe', 'janedoe@example.com', 'password321', 'neutral', '" + LocalDate.now() + "'),"
                    + "('Jay', 'Doe', 'jaydoe@example.com', 'isnotmypassword', 'neutral', '" + LocalDate.now() + "')";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (firstName, lastName, email, password, mood, memberSince) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getMood());
            statement.setString(6, user.getMemberSince());
            statement.executeUpdate();
            // Set the id of the new user
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setUserID(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE contacts SET firstName = ?, lastName = ?, email = ?, password = ?, mood = ?, memberSince = ? WHERE userID = ?");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getMood());
            statement.setString(6, user.getMemberSince());
            statement.setInt(7, user.getUserID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE userID = ?");
            statement.setInt(1, user.getUserID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IUser getUser(int userID) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE userID = ?");
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String mood = resultSet.getString("mood");
                String memberSince = resultSet.getString("memberSince");
                User user = new User(firstName, lastName, email, password, mood, memberSince);
                user.setUserID(userID);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String mood = resultSet.getString("mood");
                String memberSince = resultSet.getString("memberSince");
                User user = new User(firstName, lastName, email, password, mood, memberSince);
                user.setUserID(userID);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return users;
    }
}
