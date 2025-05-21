package base;

import java.util.Objects;

public class Player {
    private int idName;
    private String name;
    private int idLastName;
    private String lastName;

    private int idTeam;

    /**
     * Constructeur de la classe
     * @param fn Prénom
     * @param ln Nom
     * @param idTeam id de l'équipe dans la db
     */
    public Player(String fn, String ln, int idTeam)
    {
        setName(fn);
        setLastName(ln);
        setIdTeam(idTeam);
        LoggerUtil.info("Création du joueur");
    }

    /**
     * Get le nom du joueur
     * @return nom
     */
    public String getName()
    {
        return name;
    }

    public String getLastName()
    {
        return lastName;
    }
    public boolean setName(String n) {
        if (Objects.equals(n, "")){
            LoggerUtil.warning("Tentative de mettre un nom vide.");
            return false;
        }

        name = n;

        //METTRE LE LIEN AVEC L'ID
        LoggerUtil.info("Changement du prenom du joueur");
        return true;

    }
    public void setLastName(String n)
    {
        lastName = n;
        //METTRE LE LIEN AVEC L'ID
        LoggerUtil.info("Changement du nom de famille du joueur");
    }
    public void setIdTeam(int id)
    {
        idTeam = id;
        LoggerUtil.info("Changement de l'id d'équipe du joueur");
    }
    public int getIdTeam()
    {
        return idTeam;
    }

    /**
     * Print dans la console le joueur pour les tests
     */
    public void printPlayer()
    {
        System.out.printf("%s %s, I am on team %d\n",name,lastName,idTeam);
    }
}
