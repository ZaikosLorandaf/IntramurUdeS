package ca.usherbrooke.fgen.api.backend.DAO;

import ca.usherbrooke.fgen.api.backend.LoggerUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DML extends BaseDAO {

    /**
     * Permet de récupérer le rôle d'un utilisateur
     * @param username Nom d'utilisateur
     * @param password Mot de passe
     * @return Le nom du rôle ou null si non trouvé
     */
    public static String getUserRole(String username, String password) {
        String role = null;
        String query = """
            SELECT r.roleName 
            FROM user u 
            JOIN role r ON u.role_id = r.id 
            WHERE u.username = ? AND u.password = ?
        """;

        try (Connection c = getConnection();
             PreparedStatement stmt = c.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    role = rs.getString("roleName");
                }
            }
        } catch (SQLException e) {
            LoggerUtil.error("Erreur lors de la récupération du rôle de l'utilisateur.");
        }

        return role;
    }
}

