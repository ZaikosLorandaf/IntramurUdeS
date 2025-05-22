package ca.usherbrooke.fgen.api.backend.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDAO {
    private static Connection connection;

    protected static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:IntraMurUdeS.db");
                System.out.println("Opened database successfully");
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
        return connection;
    }
}
