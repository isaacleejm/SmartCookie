package cab302.iirtt.assignment1.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements IStudyGoalDAO for the StudyGoal Data Access Object that handles
 * the CRUD operations for the StudyGoal class with the database.
 */
public class StudyGoalDAO implements IStudyGoalDAO {
    private Connection connection;

    // Constructor, creates connection with database if it exists, otherwise, it will create a database.db. Then it will create a StudyGoal table if it does not already exist.

    /**
     * StudyGoalDAO Constructor, connects to the Database and creates the studyGoal table if it does not exist.
     */
    public StudyGoalDAO() {
        connection = DatabaseConnection.getInstance(); // Creates an instance of the database if it does not already exist, else get the instance
        createTable(); // Creates a StudyGoal table within the database if it does not exist
    }

    /**
     * Creates a connection to the database, deletes the current StudyGoal Table if it exists, creates a new StudyGoal Table and inserts sample data into the StudyGoal Table.
     */
    public void start() {
        connection = DatabaseConnection.getInstance();
        deleteTable();
        createTable();
        insertSampleData();
    }


    /**
     * Creates the StudyGoal table if it does not exist yet
     */
    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS studyGoals ("
                    + "studyGoalID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "studyGoalTitle VARCHAR NOT NULL,"
                    + "studyGoalDescription VARCHAR NOT NULL,"
                    + "studyGoalPriority VARCHAR NOT NULL,"
                    + "studyGoalStatus BOOLEAN NOT NULL,"
                    + "studyGoalPinned BOOLEAN NOT NULL,"
                    + "dueDate VARCHAR NOT NULL,"
                    + "lastModified DATETIME NOT NULL,"
                    + "dateCreated DATETIME NOT NULL,"
                    + "userID INTEGER NOT NULL,"
                    + "FOREIGN KEY (userID) REFERENCES users(userID)"
                    // userID is not a Foreign Key until it can be linked with the usersDatabase
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes the StudyGoal Table if it exists.
     */
    private void deleteTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "DROP TABLE IF EXISTS studyGoals";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert sample data into StudyGoal table
     */
    private void insertSampleData() {
        try {
            // Clear before inserting
            Statement clearStatement = connection.createStatement();
            String clearQuery = "DELETE FROM studyGoals";
            clearStatement.execute(clearQuery);
            Statement insertStatement = connection.createStatement();
            String insertQuery = "INSERT INTO studyGoals (studyGoalTitle, studyGoalDescription, studyGoalPriority, studyGoalStatus, studyGoalPinned, dueDate, lastModified, dateCreated, userID) VALUES "
                    + "('Revise for CAB123', 'Revise lectures 3 and 7 for CAB123 test', 'High', false, true, '" + LocalDate.of(2025, 04, 15) + "', '" + LocalDate.now() + "', '" + LocalDate.now() + "', 2),"
                    + "('Watch Lecture', 'Watch lecture 5 for CAB123 in preparation of test', 'Low', true, false, '" + LocalDate.of(2025, 04, 18) + "', '" + LocalDate.now() + "', '" + LocalDate.now() + "', 1),"
                    + "('Complete Assignment', 'Complete CAB123 assignment 1', 'High', true, true, '" + LocalDate.of(2025, 05, 25) + "', '" + LocalDate.now() + "', '" + LocalDate.now() + "', 3)";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new StudyGoal to the StudyGoal table.
     * @param studyGoal The studyGoal to add.
     */
    @Override
    public void addStudyGoal(StudyGoal studyGoal) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO studyGoals (studyGoalTitle, studyGoalDescription, studyGoalPriority, studyGoalStatus, studyGoalPinned, dueDate, lastModified, dateCreated, userID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, studyGoal.getStudyGoalTitle());
            statement.setString(2, studyGoal.getStudyGoalDescription());
            statement.setString(3, studyGoal.getStudyGoalPriority());
            statement.setBoolean(4, studyGoal.getStudyGoalStatus());
            statement.setBoolean(5, studyGoal.getStudyGoalPinned());
            statement.setString(6, studyGoal.getDueDate());
            statement.setString(7, studyGoal.getLastModified());
            statement.setString(8, studyGoal.getDateCreated());
            statement.setInt(9, studyGoal.getUserID());
            statement.executeUpdate();
            // Set the id of the new studyGoal
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                studyGoal.setStudyGoalID(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing StudyGoal's data in the database.
     * @param studyGoal The studyGoal to update.
     */
    @Override
    public void updateStudyGoal(StudyGoal studyGoal) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE studyGoals SET studyGoalTitle = ?, studyGoalDescription = ?, studyGoalPriority = ?, studyGoalStatus = ?, studyGoalPinned = ?, dueDate = ?, lastModified = ?, dateCreated = ?, userID = ? WHERE studyGoalID = ?"
            );
            statement.setString(1, studyGoal.getStudyGoalTitle());
            statement.setString(2, studyGoal.getStudyGoalDescription());
            statement.setString(3, studyGoal.getStudyGoalPriority());
            statement.setBoolean(4, studyGoal.getStudyGoalStatus());
            statement.setBoolean(5, studyGoal.getStudyGoalPinned());
            statement.setString(6, studyGoal.getDueDate());
            statement.setString(7, studyGoal.getLastModified());
            statement.setString(8, studyGoal.getDateCreated());
            statement.setInt(9, studyGoal.getUserID());
            statement.setInt(10, studyGoal.getStudyGoalID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a StudyGoal from the StudyGoal Table.
     * @param studyGoal The StudyGoal to delete.
     */
    @Override
    public void deleteStudyGoal(StudyGoal studyGoal) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM studyGoals WHERE studyGoalID = ?");
            statement.setInt(1, studyGoal.getStudyGoalID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a StudyGoal from the StudyGoal table.
     * @param studyGoalID The id of the study goal to retrieve.
     * @return If the StudyGoal with the given id was found, return the user; otherwise, return null.
     */
    @Override
    public StudyGoal getStudyGoal(int studyGoalID) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM studyGoals WHERE studyGoalID = ?");
            statement.setInt(1, studyGoalID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String studyGoalTitle = resultSet.getString("studyGoalTitle");
                String studyGoalDescription = resultSet.getString("studyGoalDescription");
                String studyGoalPriority = resultSet.getString("studyGoalPriority");
                boolean studyGoalStatus = resultSet.getBoolean("studyGoalStatus");
                boolean studyGoalPinned = resultSet.getBoolean("studyGoalPinned");
                String dueDate = resultSet.getString("dueDate");
                String lastModified = resultSet.getString("lastModified");
                String dateCreated = resultSet.getString("dateCreated");
                int userID = resultSet.getInt("userID");

                StudyGoal studyGoal = new StudyGoal(studyGoalTitle, studyGoalDescription, studyGoalPriority, studyGoalStatus, studyGoalPinned, dueDate, lastModified, dateCreated, userID);
                studyGoal.setStudyGoalID(studyGoalID);
                return studyGoal;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all StudyGoals from the StudyGoal table.
     * @return A list of all StudyGoal's in the StudyGoal table database.
     */
    @Override
    public List<StudyGoal> getAllStudyGoal() {
        List<StudyGoal> studyGoals = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM studyGoals";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int studyGoalID = resultSet.getInt("studyGoalID");
                String studyGoalTitle = resultSet.getString("studyGoalTitle");
                String studyGoalDescription = resultSet.getString("studyGoalDescription");
                String studyGoalPriority = resultSet.getString("studyGoalPriority");
                boolean studyGoalStatus = resultSet.getBoolean("studyGoalStatus");
                boolean studyGoalPinned = resultSet.getBoolean("studyGoalPinned");
                String dueDate = resultSet.getString("dueDate");
                String lastModified = resultSet.getString("lastModified");
                String dateCreated = resultSet.getString("dateCreated");
                int userID = resultSet.getInt("userID");

                StudyGoal studyGoal = new StudyGoal(studyGoalTitle, studyGoalDescription, studyGoalPriority, studyGoalStatus, studyGoalPinned, dueDate, lastModified, dateCreated, userID);
                studyGoal.setStudyGoalID(studyGoalID);
                studyGoals.add(studyGoal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studyGoals;
    }

    /**
     * Retrieves a StudyGoal from the StudyGoal table.
     * @param userID the ID of the User to view all their study goals.
     * @return If the StudyGoal with the given userID was found, return the StudyGoal
     */
    @Override
    public List<StudyGoal> getStudyGoalsByUserID(int userID) {
        List<StudyGoal> studyGoals = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM studyGoals WHERE userID = ?");
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int studyGoalID = resultSet.getInt("studyGoalID");
                String studyGoalTitle = resultSet.getString("studyGoalTitle");
                String studyGoalDescription = resultSet.getString("studyGoalDescription");
                String studyGoalPriority = resultSet.getString("studyGoalPriority");
                boolean studyGoalStatus = resultSet.getBoolean("studyGoalStatus");
                boolean studyGoalPinned = resultSet.getBoolean("studyGoalPinned");
                String dueDate = resultSet.getString("dueDate");
                String lastModified = resultSet.getString("lastModified");
                String dateCreated = resultSet.getString("dateCreated");

                StudyGoal studyGoal = new StudyGoal(studyGoalTitle, studyGoalDescription, studyGoalPriority, studyGoalStatus, studyGoalPinned, dueDate, lastModified, dateCreated, userID);
                studyGoal.setStudyGoalID(studyGoalID);
                studyGoals.add(studyGoal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studyGoals;
    }

    /**
     *
     * @param userID the ID of the user to view all their completed studyGoals.
     * @return A list of StudyGoal's that have their studyGoalStatus = true (Completed) and are found by the userID of the user.
     */
    @Override
    public List<StudyGoal> getCompletedStudyGoalsByUserID(int userID) {
        List<StudyGoal> studyGoals = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM studyGoals WHERE userID = ? AND studyGoalStatus = true");
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int studyGoalID = resultSet.getInt("studyGoalID");
                String studyGoalTitle = resultSet.getString("studyGoalTitle");
                String studyGoalDescription = resultSet.getString("studyGoalDescription");
                String studyGoalPriority = resultSet.getString("studyGoalPriority");
                boolean studyGoalStatus = resultSet.getBoolean("studyGoalStatus");
                boolean studyGoalPinned = resultSet.getBoolean("studyGoalPinned");
                String dueDate = resultSet.getString("dueDate");
                String lastModified = resultSet.getString("lastModified");
                String dateCreated = resultSet.getString("dateCreated");

                StudyGoal studyGoal = new StudyGoal(studyGoalTitle, studyGoalDescription, studyGoalPriority, studyGoalStatus, studyGoalPinned, dueDate, lastModified, dateCreated, userID);
                studyGoal.setStudyGoalID(studyGoalID);
                studyGoals.add(studyGoal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studyGoals;
    }
}
