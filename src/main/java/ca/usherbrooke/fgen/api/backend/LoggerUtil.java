package ca.usherbrooke.fgen.api.backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private static final Path LOG_PATH = Paths.get(System.getProperty("user.dir"), "logs", "app.log");
    private static final boolean DISPLAY_IN_CONSOLE = true;
    private static boolean displayPath = true;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final boolean IS_TEST_ENV = isTestEnv();
    private static boolean isTestEnv() {
        String prop = System.getProperty("java.class.path");
        return prop != null && prop.contains("test");
    }

    static {
        try {
            File logDir = LOG_PATH.getParent().toFile();
            if (!logDir.exists()) {
                boolean created = logDir.mkdirs();
                if (!created) {
                    System.err.println("Erreur : impossible de créer le dossier de logs.");
                }
            }

            File logFile = LOG_PATH.toFile();
            if (!logFile.exists()) {
                boolean created = logFile.createNewFile();
                if (!created) {
                    System.err.println("Erreur : impossible de créer le fichier de log.");
                }
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de l'initialisation du fichier de log : " + e.getMessage());
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

    private static void log(String level, String message) {
        if (IS_TEST_ENV) {
            return;
        }

        String timestamp = LocalDateTime.now().format(formatter);
        String logMessage = String.format("[%s] [%s] %s", timestamp, level, message);

        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_PATH.toFile(), true))) {
            out.println(logMessage);
        } catch (IOException e) {
            System.err.println("Impossible d'écrire dans le fichier de log : " + e.getMessage());
        }

        if (displayPath) {
            displayPath = false;
            System.out.println(AnsiColors.RED + "[IMPORTANT] path file : " + LOG_PATH.toAbsolutePath() + AnsiColors.RESET);
        }

        if (DISPLAY_IN_CONSOLE) {
            String color = switch (level) {
                case "WARNING" -> AnsiColors.YELLOW;
                case "ERROR" -> AnsiColors.RED;
                default -> "";
            };

            System.out.println(color + logMessage + AnsiColors.RESET);
        }
    }
}
