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
//        deleteTable(); // Used for testing, TO BE REMOVED LATER
        createTable();
//        insertSampleData(); // Used for testing, TO BE REMOVED LATER
    }

    public void start() {
        connection = DatabaseConnection.getInstance();
        deleteTable();
        createTable();
        insertSampleData();
    }

    // Creates the AI responses table if it does not exist yet
    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS AIResponses ("
                    + "responseID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "responseType VARCHAR NOT NULL,"
                    + "responseRating INTEGER NOT NULL,"
                    + "responseDate DATETIME NOT NULL,"
                    + "responseText VARCHAR NOT NULL,"
                    + "userInput VARCHAR NOT NULL,"
                    + "favourite BOOLEAN NOT NULL,"
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
            String insertQuery = "INSERT INTO AIResponses (responseType, responseRating, responseDate, responseText, userInput, favourite, userID) VALUES "
                    + "('MOTIVATIONAL_QUOTE', 3, '" + LocalDate.now() + "', 'Artificial Intelligence (AI) is a vast and rapidly evolving field within computer science that focuses on creating machines capable of performing tasks that typically require human intelligence.', 'Can you tell me more about AI?', 'false', 1),"
                    + "('ADVICE_TIP', 5, '" + LocalDate.now() + "', 'A random sentence that was generated from the prompt.', 'Can you generate some random sentence.', 'true', 2),"
                    + "('FORTUNE_COOKIE', 9, '" + LocalDate.now() + "', 'one - two - three - four - five - six - seven - eight - nine - ten', 'Can you count from one to ten like this, one - two -...', 'false', 3)";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAIResponse(AIResponse aiResponse) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO AIResponses (responseType, responseRating, responseDate, responseText, userInput, favourite, userID) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, aiResponse.getResponseType().name());
            statement.setInt(2, aiResponse.getResponseRating());
            statement.setString(3, aiResponse.getResponseDate());
            statement.setString(4, aiResponse.getResponseText());
            statement.setString(5, aiResponse.getUserInput());
            statement.setBoolean(6, aiResponse.getFavourite());
            statement.setInt(7, aiResponse.getUserID());


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
            PreparedStatement statement = connection.prepareStatement("UPDATE AIResponses SET responseType = ?, responseRating = ?, responseDate = ?, responseText = ?, userInput = ?, favourite = ?, userID = ? WHERE responseID = ?");
            statement.setInt(1, aiResponse.getAIResponseID());
            statement.setString(2, aiResponse.getResponseType().name());
            statement.setInt(3, aiResponse.getResponseRating());
            statement.setString(4, aiResponse.getResponseDate());
            statement.setString(5, aiResponse.getResponseText());
            statement.setString(6, aiResponse.getUserInput());
            statement.setBoolean(7, aiResponse.getFavourite());
            statement.setInt(8, aiResponse.getUserID());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAIResponse(AIResponse aiResponse) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM AIResponses WHERE responseID = ?");
            statement.setInt(1, aiResponse.getAIResponseID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AIResponse getAIResponse(int aiResponseID) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM AIResponses WHERE responseID = ?");
            statement.setInt(1, aiResponseID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int AIResponseID = resultSet.getInt("responseID");
                AIResponse.ResponseType responseType = AIResponse.ResponseType.valueOf(resultSet.getString("responseType"));
                int responseRating = resultSet.getInt("responseRating");
                String responseDate = resultSet.getString("responseDate");
                String responseText = resultSet.getString("responseText");
                String userInput = resultSet.getString("userInput");
                boolean favourite = resultSet.getBoolean("favourite");
                int userID = resultSet.getInt("userID");

                AIResponse aiResponse = new AIResponse(responseType, responseRating, responseDate, responseText, userInput, favourite, userID);
                aiResponse.setAIResponseID(AIResponseID);
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
            String query = "SELECT * FROM AIResponses";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int AIResponseID = resultSet.getInt("responseID");
                AIResponse.ResponseType responseType = AIResponse.ResponseType.valueOf(resultSet.getString("responseType"));
                int responseRating = resultSet.getInt("responseRating");
                String responseDate = resultSet.getString("responseDate");
                String responseText = resultSet.getString("responseText");
                String userInput = resultSet.getString("userInput");
                boolean favourite = resultSet.getBoolean("favourite");
                int userID = resultSet.getInt("userID");

                AIResponse aiResponse = new AIResponse(responseType, responseRating, responseDate, responseText, userInput, favourite, userID);
                aiResponse.setAIResponseID(AIResponseID);
                aiResponses.add(aiResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return aiResponses;
    }

    @Override
    public List<AIResponse> getAIResponsesByUserID(int userID) {
        List<AIResponse> aiResponses = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM AIResponses WHERE userID = ?");
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int AIResponseID = resultSet.getInt("responseID");
                AIResponse.ResponseType responseType = AIResponse.ResponseType.valueOf(resultSet.getString("responseType"));
                int responseRating = resultSet.getInt("responseRating");
                String responseDate = resultSet.getString("responseDate");
                String responseText = resultSet.getString("responseText");
                String userInput = resultSet.getString("userInput");
                boolean favourite = resultSet.getBoolean("favourite");

                AIResponse aiResponse = new AIResponse(responseType, responseRating, responseDate, responseText, userInput, favourite, userID);
                aiResponse.setAIResponseID(AIResponseID);
                aiResponses.add(aiResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aiResponses;
    }
}
