package ca.usherbrooke.fgen.api.backend.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DDL extends BaseDAO {

    public static void recreateDataBase() {
        try {
            Connection c = getConnection();
            if (c != null) {
                Statement stmt = c.createStatement();

                // TODO: Ajouter le schema de la base de donnée

                stmt.close();
                c.close();
                System.out.println("Database recreated successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

