package cab302.iirtt.assignment1.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AIResponseDAO implements IAIResponseDAO{
    private Connection connection;

    // Constructor, creates connection with database if it exists, otherwise, it will create a database.db. Then it will create an AI responses table if it does not already exist.
    public AIResponseDAO() {
        connection = DatabaseConnection.getInstance();
        deleteTable(); // Used for testing, TO BE REMOVED LATER
        createTable();
        insertSampleData(); // Used for testing, TO BE REMOVED LATER
    }

    // Creates the AI responses table if it does not exist yet
    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS AIResponses ("
                    + "responseID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "responseType VARCHAR NOT NULL,"
                    + "responseRating INTEGER,"
                    + "responseDate DATETIME NOT NULL,"
                    + "responseText VARCHAR NOT NULL,"
                    + "userInput VARCHAR NOT NULL"
                    + "userID INTEGER NOT NULL,"
                    + "FOREIGN KEY (userID) REFERENCES users(userID)"
                    // userID is not a Foreign Key until it can be linked with the usersDatabase
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // DROPs the AIResponses table if it exists
    private void deleteTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "DROP TABLE IF EXISTS AIResponses";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // This method inserts sample data into AIResponse table and will be removed when submitting. NOTE: This method does not consider duplicate data
    private void insertSampleData() {
        try {
            // Clear before inserting
            Statement clearStatement = connection.createStatement();
            String clearQuery = "DELETE FROM AIResponses";
            clearStatement.execute(clearQuery);
            Statement insertStatement = connection.createStatement();
            String insertQuery = "INSERT INTO AIResponses (responseType, responseRating, responseDate, responseTime, responseText, userInput, userID) VALUES "
                    + "('formal', 3, '" + LocalDate.now() + "', 'Artificial Intelligence (AI) is a vast and rapidly evolving field within computer science that focuses on creating machines capable of performing tasks that typically require human intelligence.', 'Can you tell me more about AI?', '1'),"
                    + "('funny', '2', '" + LocalDate.now() + "', 'Multiply the diagonals and do some subtraction magic on the  cross-products, and boom - determinant', 'in 15 words, how do i find the determinant of a matrix?', 2),"
                    + "('encouraging', '5', '" + LocalDate.now() + "', 'Just open IntelliJ and click 'new project' then name it and then hit 'finish' You got this!', 'how do i create a new project in IntelliJ', 3)";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAIResponse(AIResponse aiResponse) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO aiResponses (responseType, responseRating, responseDate, responseTime, responseText, userInput, userID) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, aiResponse.getAIResponseID());
            statement.setString(2, aiResponse.getResponseType().name());
            statement.setInt(3, aiResponse.getResponseRating());
            statement.setString(4, aiResponse.getResponseDate());
            statement.setString(5, aiResponse.getResponseText());
            statement.setString(6, aiResponse.getUserInput());
            statement.setInt(7, aiResponse.getUserID());
            statement.setBoolean(8, aiResponse.getFavourite());


            statement.executeUpdate();
            // Set the id of the new user
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                aiResponse.setAIResponseID(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAIResponse(AIResponse aiResponse) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE aiResponses SET responseType = ?, responseRating = ?, responseDate = ?, responseTime = ?, responseText = ?, userInput = ?, userID = ? WHERE aiResponseID = ?");
            statement.setInt(1, aiResponse.getAIResponseID());
            statement.setString(2, aiResponse.getResponseType().name());
            statement.setInt(3, aiResponse.getResponseRating());
            statement.setString(4, aiResponse.getResponseDate());
            statement.setString(5, aiResponse.getResponseText());
            statement.setString(6, aiResponse.getUserInput());
            statement.setInt(7, aiResponse.getUserID());
            statement.setBoolean(8, aiResponse.getFavourite());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAIResponse(AIResponse aiResponse) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM aiResponses WHERE aiResponseID = ?");
            statement.setInt(1, aiResponse.getAIResponseID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AIResponse getAIResponse(int aiResponseID) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM aiResponses WHERE aiResponseID = ?");
            statement.setInt(1, aiResponseID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("responseID");
                AIResponse.ResponseType type = AIResponse.ResponseType.valueOf(resultSet.getString("responseType"));
                int rating = resultSet.getInt("responseRating");
                String date = resultSet.getString("responseDate");
                String text = resultSet.getString("responseText");
                String input = resultSet.getString("userInput");
                int userID = resultSet.getInt("userID");
                boolean favourite = resultSet.getBoolean("favourite");

                AIResponse aiResponse = new AIResponse(id, type, rating, date, text, input, userID, favourite);
                aiResponse.setAIResponseID(id);
                return aiResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AIResponse> getAllAIResponse() {
        List<AIResponse> aiResponses = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM aiResponses";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("responseID");
                AIResponse.ResponseType type = AIResponse.ResponseType.valueOf(resultSet.getString("responseType"));
                int rating = resultSet.getInt("responseRating");
                String date = resultSet.getString("responseDate");
                String text = resultSet.getString("responseText");
                String input = resultSet.getString("userInput");
                int userID = resultSet.getInt("userID");
                boolean favourite = resultSet.getBoolean("favourite");

                AIResponse aiResponse = new AIResponse(id, type, rating, date, text, input, userID, favourite);
                aiResponse.setAIResponseID(id);
                aiResponses.add(aiResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return aiResponses;
    }
}
