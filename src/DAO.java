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

}