package cab302.iirtt.assignment1.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudyMaterialDAO implements IStudyMaterialDAO {
    private Connection connection;

    /**
     * StudyMaterialDAO Constructor, connects to the Database and creates the StudyMaterial Table if it does not exist.
     */
    public StudyMaterialDAO() {
        connection = DatabaseConnection.getInstance();
        createTable();
    }

    /**
     * Creates a connection to the database, deletes the current StudyMaterial Table if it exists, creates a new StudyMaterial Table and inserts sample data into the StudyMaterial Table.
     */
    public void start() {
        connection = DatabaseConnection.getInstance();
        deleteTable();
        createTable();
        insertSampleData();
    }

    /**
     * Creates the StudyMaterial Table if it does not exist.
     */
    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS studyMaterials ("
                    + "studyMaterialID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "studyMaterialTitle VARCHAR NOT NULL,"
                    + "studyMaterialSubject VARCHAR NOT NULL,"
                    + "studyMaterialDescription VARCHAR NOT NULL,"
                    + "dateModified VARCHAR NOT NULL,"
                    + "dateCreated VARCHAR NOT NULL,"
                    + "userID DATETIME NOT NULL,"
                    + "FOREIGN KEY (userID) REFERENCES users(userID)"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes the Study Material Table if it exists.
     */
    private void deleteTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "DROP TABLE IF EXISTS studyMaterials";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert Sample Data into the StudyMaterial Table
     */
    private void insertSampleData() {
        try {
            // Clear before inserting
            Statement clearStatement = connection.createStatement();
            String clearQuery = "DELETE FROM studyMaterials";
            clearStatement.execute(clearQuery);
            Statement insertStatement = connection.createStatement();
            String insertQuery = "INSERT INTO studyMaterials (studyMaterialTitle, studyMaterialSubject, studyMaterialDescription, dateModified, dateCreated, userID) VALUES "
                    + "('CAB123 Lecture 7', 'CAB123 Intro to JavaFX', 'JavaFX is a Java library and a GUI toolkit designed to develop and facilitate Rich Internet Applications (RIA), web applications, and desktop applications. It provides a comprehensive set of features and functionalities that allow developers to create visually appealing and interactive applications that can run on multiple platforms, including Windows, Linux, iOS, Android, and various devices such as desktops, web browsers, mobile phones, TVs, and tablets.', '" + LocalDate.of(2025, 5, 2) + "', '" + LocalDate.of(2025, 5, 5) + "', '2'),"
                    + "('ABC123 Tutorial 1', 'ABC123 Intro to Alphabets', 'Learning about the Alphabets. A - Apple, B - Ball, C - Cat, D - Dog, E - Elephant, F - Fish, G - Goat, H - Hat, I - Ice, J - Jam, K - Kite, L - Lion, M - Moon, N - Nose, O - Octopus, P - Pig, Q - Queen, R - Rabbit, S - Sun, T - Tiger, U - Umbrella, V - Violin, W - Whale, X - Xylophone, Y - Yak, Z - Zebra', '" + LocalDate.of(2025, 5, 3) + "', '" + LocalDate.of(2025, 5, 3) + "', '1'),"
                    + "('CAB123 Tutorial 7', 'CAB123 Intro to JavaFX', 'JavaFX is a modern, open-source framework developed by Oracle for building rich graphical user interfaces (GUIs) in Java applications. It is designed to create visually appealing and interactive desktop, mobile, and embedded applications. JavaFX is a successor to the older Swing and Abstract Window Toolkit (AWT) libraries, offering more advanced features and capabilities.', '" + LocalDate.of(2025, 4, 27) + "', '" + LocalDate.of(2025, 4, 28) + "', '2')";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new StudyMaterial object to the database.
     *
     * @param studyMaterial The Study Material to add
     */
    @Override
    public void addStudyMaterial(StudyMaterial studyMaterial) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO studyMaterials (studyMaterialTitle, studyMaterialSubject, studyMaterialDescription, dateModified, dateCreated, userID) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, studyMaterial.getStudyMaterialTitle());
            statement.setString(2, studyMaterial.getStudyMaterialSubject());
            statement.setString(3, studyMaterial.getStudyMaterialDescription());
            statement.setString(4, studyMaterial.getDateModified());
            statement.setString(5, studyMaterial.getDateCreated());
            statement.setInt(6, studyMaterial.getUserID());
            statement.executeUpdate();
            // Set the id of the new study material
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                studyMaterial.setUserID(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing Study Material in the database
     *
     * @param studyMaterial The Study Material to update
     */
    @Override
    public void updateStudyMaterial(StudyMaterial studyMaterial) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE studyMaterials SET studyMaterialTitle = ?, studyMaterialSubject = ?, studyMaterialDescription = ?, dateModified = ?, dateCreated = ?, userID = ? WHERE studyMaterialID = ?");
            statement.setString(1, studyMaterial.getStudyMaterialTitle());
            statement.setString(2, studyMaterial.getStudyMaterialSubject());
            statement.setString(3, studyMaterial.getStudyMaterialDescription());
            statement.setString(4, studyMaterial.getDateModified());
            statement.setString(5, studyMaterial.getDateCreated());
            statement.setInt(6, studyMaterial.getUserID());
            statement.setInt(7, studyMaterial.getStudyMaterialID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a Study Material from the database.
     *
     * @param studyMaterialID The id of hte Study Material to delete.
     */
    @Override
    public void deleteStudyMaterialByID(int studyMaterialID) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM studyMaterials WHERE studyMaterialID = ?");
            statement.setInt(1, studyMaterialID);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a Study Material from the database.
     *
     * @param studyMaterialID The id of the Study Material to retrieve.
     * @return If the Study Material was found, return the Study Material with the given id; otherwise, return null.
     */
    @Override
    public StudyMaterial getStudyMaterialByID(int studyMaterialID) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM studyMaterials WHERE studyMaterialID = ?");
            statement.setInt(1, studyMaterialID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String studyMaterialTitle = resultSet.getString("studyMaterialTitle");
                String studyMaterialSubject = resultSet.getString("studyMaterialSubject");
                String studyMaterialDescription = resultSet.getString("studyMaterialDescription");
                String dateModified = resultSet.getString("dateModified");
                String dateCreated = resultSet.getString("dateCreated");
                int userID = resultSet.getInt("userID");
                StudyMaterial studyMaterial = new StudyMaterial(studyMaterialTitle, studyMaterialSubject, studyMaterialDescription, dateModified, dateCreated, userID);
                studyMaterial.setStudyMaterialID(studyMaterialID);
                return studyMaterial;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all Study Materials from the database.
     *
     * @return A list of all Study Materials from the database.
     */
    @Override
    public List<StudyMaterial> getAllStudyMaterial() {
        List<StudyMaterial> studyMaterials = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM studyMaterials";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int studyMaterialID = resultSet.getInt("studyMaterialID");
                String studyMaterialTitle = resultSet.getString("studyMaterialTitle");
                String studyMaterialSubject = resultSet.getString("studyMaterialSubject");
                String studyMaterialDescription = resultSet.getString("studyMaterialDescription");
                String dateModified = resultSet.getString("dateModified");
                String dateCreated = resultSet.getString("dateCreated");
                int userID = resultSet.getInt("userID");
                StudyMaterial studyMaterial = new StudyMaterial(studyMaterialTitle, studyMaterialSubject, studyMaterialDescription, dateModified, dateCreated, userID);
                studyMaterial.setStudyMaterialID(studyMaterialID);
                studyMaterials.add(studyMaterial);
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return studyMaterials;
    }

    /**
     * Retrieves all Study Materials with the given userID from the database.
     *
     * @param userID The id of the user which the Study Materials belongs to.
     * @return A list of Study Materials with the given id from the database.
     */
    @Override
    public List<StudyMaterial> getStudyMaterialByUserID(int userID) {
        List<StudyMaterial> studyMaterials = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM studyMaterials WHERE userID = ?");
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int studyMaterialID = resultSet.getInt("studyMaterialID");
                String studyMaterialTitle = resultSet.getString("studyMaterialTitle");
                String studyMaterialSubject = resultSet.getString("studyMaterialSubject");
                String studyMaterialDescription = resultSet.getString("studyMaterialDescription");
                String dateModified = resultSet.getString("dateModified");
                String dateCreated = resultSet.getString("dateCreated");
                StudyMaterial studyMaterial = new StudyMaterial(studyMaterialTitle, studyMaterialSubject, studyMaterialDescription, dateModified, dateCreated, userID);
                studyMaterial.setStudyMaterialID(studyMaterialID);
                studyMaterials.add(studyMaterial);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studyMaterials;
    }
}
