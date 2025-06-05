package ca.usherbrooke.fgen.api.backend;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe qui permet de logger toutes les erreurs possibles du programmes
 * Objectif: Répertoriez les erreurs invisibles dans un fichier
 * Exemples d'utilisation
 *         base.LoggerUtil.info("Application démarrée");
 *         base.LoggerUtil.warning("Attention, action risquée !");
 *         base.LoggerUtil.error("Une erreur est survenue");
 */
public class LoggerUtil {

    private static final String LOG_FILE = "../../../../src/logs/app.log";
    private static final boolean DISPLAY_IN_CONSOLE = true;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static {
        // Crée le dossier logs si nécessaire
        System.out.println("Répertoire courant: " + System.getProperty("user.dir"));
        java.io.File logDir = new java.io.File("logs");
        if (!logDir.exists()) {
            logDir.mkdir();
        }
    }

    public static void info(String message) {
        log("INFO", message);
    }

    public static void warning(String message) {
        log("WARNING", message);
    }

    public static void error(String message) {
        log("ERROR", message);
    }

    /**
     * Permet d'écrire des logs dans un fichier
     * @param level
     * @param message
     */
    private static void log(String level, String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logMessage = String.format("[%s] [%s] %s", timestamp, level, message);

        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            out.println(logMessage);
        } catch (IOException e) {
            System.err.println("Impossible d'écrire dans le fichier de log : " + e.getMessage());
        }

        if (DISPLAY_IN_CONSOLE){
            System.out.println(logMessage);
        }
    }
}
