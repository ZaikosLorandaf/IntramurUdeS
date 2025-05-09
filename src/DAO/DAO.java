package DAO;

import base.LoggerUtil;

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
            LoggerUtil.error(e.getClass().getName() + ": " + e.getMessage());
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
            LoggerUtil.warning("Réinitialisation de la base de donnée!");
            Connection c = null;
            c = DAO.getConnection();
            if ( c != null )
            {
                Statement stmt = c.createStatement();
                String sql = "DROP TABLE IF EXISTS sport";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE sport (" +
                        "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "  name TEXT," +
                        "  created_at TIMESTAMP" +
                        ");";
                stmt.executeUpdate(sql);

                sql = "DROP TABLE IF EXISTS league";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE league (" +
                        "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "  name TEXT," +
                        "  day TEXT," +
                        "  timeStart TIME," +
                        "  timeEnd TIME," +
                        "  sport_id INTEGER NOT NULL," +
                        "  created_at TIMESTAMP," +
                        "  FOREIGN KEY (sport_id) REFERENCES sport(id)" +
                        ");";
                stmt.executeUpdate(sql);


                sql = "DROP TABLE IF EXISTS team";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE team (" +
                        "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "  name TEXT," +
                        "  league_id INTEGER NOT NULL," +
                        "  created_at TIMESTAMP," +
                        "  FOREIGN KEY (league_id) REFERENCES league(id)" +
                        ");";
                stmt.executeUpdate(sql);

                sql = "DROP TABLE IF EXISTS player";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE player (" +
                        "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "  name TEXT," +
                        "  lastName TEXT," +
                        "  team_id INTEGER NOT NULL," +
                        "  created_at TIMESTAMP," +
                        "  FOREIGN KEY (team_id) REFERENCES team(id)" +
                        ");";
                stmt.executeUpdate(sql);


                sql = "DROP TABLE IF EXISTS generalStat";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE generalStat (" +
                        "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "  statement TEXT," +
                        "  created_at TIMESTAMP" +
                        ");";
                stmt.executeUpdate(sql);


                sql = "DROP TABLE IF EXISTS match";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE match (" +
                        "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "  team1_id INTEGER," +
                        "  team2_id INTEGER," +
                        "  matchDay TEXT," +
                        "  matchHour TIME," +
                        "  created_at TIMESTAMP," +
                        "  FOREIGN KEY (team1_id) REFERENCES team(id)," +
                        "  FOREIGN KEY (team2_id) REFERENCES team(id)" +
                        ");";
                stmt.executeUpdate(sql);


                sql = "DROP TABLE IF EXISTS season";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE season (" +
                        "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "  seasonYear INTEGER NOT NULL," +
                        "  timePrecision TEXT NOT NULL," +
                        "  created_at TIMESTAMP," +
                        "  UNIQUE (seasonYear, timePrecision)" +
                        ");";
                stmt.executeUpdate(sql);


                sql = "DROP TABLE IF EXISTS playerStat";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE playerStat (" +
                        "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "  value TEXT," +
                        "  generalStat_id INTEGER NOT NULL," +
                        "  player_id INTEGER NOT NULL," +
                        "  match_id INTEGER," +
                        "  saison_id INTEGER," +
                        "  created_at TIMESTAMP," +
                        "  FOREIGN KEY (generalStat_id) REFERENCES generalStat(id)," +
                        "  FOREIGN KEY (player_id) REFERENCES player(id)," +
                        "  FOREIGN KEY (match_id) REFERENCES match(id)," +
                        "  FOREIGN KEY (saison_id) REFERENCES season(id)    " +
                        ");";
                stmt.executeUpdate(sql);



                sql = "DROP TABLE IF EXISTS teamStat";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE teamStat (" +
                        "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "  value TEXT," +
                        "  generalStat_id INTEGER NOT NULL," +
                        "  team_id INTEGER NOT NULL," +
                        "  match_id INTEGER," +
                        "  season_id INTEGER," +
                        "  created_at TIMESTAMP," +
                        "  FOREIGN KEY (generalStat_id) REFERENCES generalStat(id)," +
                        "  FOREIGN KEY (team_id) REFERENCES team(id)," +
                        "  FOREIGN KEY (match_id) REFERENCES match(id)," +
                        "  FOREIGN KEY (season_id) REFERENCES season(id)" +
                        ");";
                stmt.executeUpdate(sql);



                sql = "DROP TABLE IF EXISTS statSport";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE statSport (" +
                        "  sport_id INTEGER NOT NULL," +
                        "  generalStat_id INTEGER NOT NULL," +
                        "  created_at TIMESTAMP," +
                        "  PRIMARY KEY (sport_id, generalStat_id)," +
                        "  FOREIGN KEY (sport_id) REFERENCES sport(id)," +
                        "  FOREIGN KEY (generalStat_id) REFERENCES generalStat(id)" +
                        ");";
                stmt.executeUpdate(sql);


                sql = "DROP TABLE IF EXISTS [role]";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE role (" +
                        "  id INTEGER PRIMARY KEY," +
                        "  roleName TEXT NOT NULL," +
                        "  created_at TIMESTAMP" +
                        ");";
                stmt.executeUpdate(sql);



                sql = "DROP TABLE IF EXISTS [user]";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE user (" +
                        "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "  username TEXT," +
                        "  password TEXT," +
                        "  role_id INTEGER," +
                        "  created_at TIMESTAMP," +
                        "  FOREIGN KEY (role_id) REFERENCES role(id)" +
                        ");";
                stmt.executeUpdate(sql);


                stmt.close();
                c.close();
            }
        }
        catch (SQLException e) {
            LoggerUtil.error(e.getClass().getName() + ": " + e.getMessage());
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