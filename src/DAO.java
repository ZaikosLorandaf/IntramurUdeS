import java.sql.*;

public class DAO {
    public static Connection getConnection()
    {
        Connection c = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:IntraMurUdeS.db");
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        System.out.println("Opened database successfully");
        return c;
    }

    /**
     * Permet de recréer la data base, Admin seulement
     */
    public static void recreateDataBase()
    {
        try
        {
            Connection c = null;
            c = DAO.getConnection();
            if ( c != null )
            {
                Statement stmt = c.createStatement();
                String sql = "DROP TABLE IF EXISTS test";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE test " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " NAME           TEXT    NOT NULL, " +
                        " AGE            INT     NOT NULL, " +
                        " ADDRESS        CHAR(50), " +
                        " SALARY         REAL)";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Database recreated successfully");
    }

    /** fixme: Not tested
     * Permet de récupérer le rôle d'un utilisateur
     * @param username Nom d'utilisateur
     * @param password Mot de passe
     * @return Le nom du rôle ou null si non trouvé
     */
    public static String getUser(String username, String password) {
        String role = null;
        String query = """
            SELECT  r.role_name 
            FROM    user u 
            JOIN    role r ON u.role_id = r.id 
            WHERE   u.username = ? AND u.password = ?
        """;

        try (Connection c = DAO.getConnection();
             PreparedStatement stmt = c.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    role = rs.getString("role_name");
                }
            }
        } catch (SQLException e) {
            LoggerUtil.error("Erreur lors de la récupération du rôle de l'utilisateur.");
        }
        return role;
    }
}