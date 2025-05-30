package ca.usherbrooke.fgen.api.backend;

import java.util.List;

public class ListTeam extends ListTemplate<Team, String>  {
    /**
     * Constructeur par defaut vide.
     */
    public ListTeam() {
        LoggerUtil.info("Création de la liste d'équipe");
    }

    /**
     * Ajouter une équipe au vecteur
     *
     * @param team L'équipe à ajouter
     *
     * @return vrai si equipe ajoutee
     */
    public boolean addTeam(Team team) {

        switch (addItem(team)) {
            case 0:
                ListSport.addTeamMap(team);
                LoggerUtil.info("Ajout de l'équipe " + team.getName());
                return true;
            case 1:
                LoggerUtil.warning("Le id de l'équipe " + team.getName() + " (" + team.getId() + ") est déjà dans présent.");
                return false;
            default:
                return false;
        }
    }

    /**
     * Ajouter une liste équipe à la liste
     *
     * @param teams Liste des équipes à ajouter
     *
     * @return int
     */
    public int addTeam(List<Team> teams) {
        return addItems(teams);
    }

    public boolean removeTeam(int id) {
        if (removeItem(id)) {
            LoggerUtil.warning("Retrait de l'équipe " + getItem(id).getName() + "(id: " + id + ").");
            ListSport.removeTeamMap(getItem(id));
            return true;
        } else {
            LoggerUtil.warning("Échec du retrait de l'équipe " + getItem(id).getName() + "(id: " + id + ").");
            return false;
        }
    }


    /**
     * Retirer une équipe de la liste en fonction de l'équipe
     *
     * @param team Équipe à retirer
     *
     * @return true si une équipe a été retirée, sinon false
     */
    public boolean removeTeam(Team team) {
        return removeTeam(team.getId());
    }


    /**
     * Affiche les equipes de la liste dans la console. Fonction test de la classe Team
     */
    public void printListTeam() {
        if(getMapSize() <= 0) {
            System.out.println("Pas d'équipe");
            return;
        }
        System.out.println("-----base.ListTeam-----");
        for(int i : getMapIds()) {
            getTeam(i).printTeam();
        }
        System.out.println("-----Fin-----");
    }

    /**
     * Enleve toutes les équipes dans la liste
     */
    public void clearList(){
        clearMap();
    }


    // Getter
    public Team getTeam(int id) { return getItem(id); }
    public Team getTeam(String name) {return getItem(name); }
    public int getSize() {return getMapSize(); }

    public List<Integer> getTeamIds() { return getMapIds(); }

    public int getId(Team team) { return team.getId(); }
    public String getName(Team team) { return team.getName(); };
}

/*
public class ListTeam {

    @Inject
    OGClass ogClass;

    private Map<Integer, Team> mapId;
    private Map<String, Integer> mapNomId;

    /**
     * Constructeur de base
     *
public ListTeam() {
    this.mapId = new HashMap<>();
    this.mapNomId = new HashMap<>();
    LoggerUtil.info("Création de la liste d'équipe");
}

/**
 * Ajouter une équipe au vecteur
 *
 * @param team L'équipe à ajouter
 *
public boolean addTeam(Team team) {
    if (!this.mapId.containsKey(team.getId()) && !this.mapNomId.containsKey(team.getName())) {
        this.mapId.put(team.getId(), team);
        ListSport.addTeamMap(team);
        LoggerUtil.info("Ajout de l'équipe " + team.getName());
        return true;
    } else {
        LoggerUtil.warning("Le id de l'équipe " + team.getName() + " (" + team.getId() + ") est déjà dans présent.");
        return false;
    }

    public Map<Integer, Team> getMapId() {
        return mapId;
    }

    public Map<String, Integer> getMapNomId() {
        return mapNomId;
    }
}

/**
 * Ajouter une liste équipe à la liste
 * @param teams Liste des équipes à ajouter
 * @return
 *
public int addTeam(List<Team> teams) {
    int counter = 0;
    for (Team team : teams) {
        if (addTeam(team)) {
            counter++;
        }
    }
    return counter;
}



public boolean removeTeam(int id) {
    if(mapId.containsKey(id) && mapNomId.containsKey(mapId.get(id).getName())) {
        LoggerUtil.warning("Retrait de l'équipe " + mapId.get(id).getName() + "(id: " + id + ").");
        ListSport.removeTeamMap(mapId.get(id));
        mapNomId.remove(mapId.get(id).getName());
        mapId.remove(id);

        return true;
    } else {
        LoggerUtil.warning("Échec du retrait de l'équipe " + mapId.get(id).getName() + "(id: " + id + ").");
        return false;
    }
}


/**
 * Retirer une équipe de la liste en fonction de l'équipe
 *
 * @param team Équipe à retirer
 * @return true si une équipe a été retirée, sinon false
 *
public boolean removeTeam(Team team) {
    return removeTeam(team.getId());
}


/**
 * Fonction test de la classe Team
 *
public void printListTeam() {
    if(mapId.size() <= 0) {
        System.out.println("Pas d'équipe");
        return;
    }
    System.out.println("-----base.ListTeam-----");
    for(int i : mapId.keySet()) {
        getTeam(i).printTeam();
    }
    System.out.println("-----Fin-----");
}


/**
 * Enleve toutes les équipes dans la liste
 *
public void clearList(){
    this.mapId.clear();
}


// Getter
public Team getTeam(int id)
{
    return mapId.getOrDefault(id, null);
}
public Team getTeam(String name)
{
    return this.getTeam(this.mapNomId.getOrDefault(name, null));
}
public int getSize()
{
    return mapId.size();
}
}
 */
