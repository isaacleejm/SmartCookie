package cab302.iirtt.assignment1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection creates the database if not yet created, and allows the connection between the database and DAOs
 * **/
public class DatabaseConnection {
    private static Connection instance = null;

    private DatabaseConnection() {
        String url = "jdbc:sqlite:database.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            new DatabaseConnection();
        }
        return instance;
    }
}
