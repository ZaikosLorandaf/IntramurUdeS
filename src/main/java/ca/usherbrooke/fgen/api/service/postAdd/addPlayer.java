package ca.usherbrooke.fgen.api.service.postAdd;

import io.smallrye.common.constraint.NotNull;

public class addPlayer {
    @NotNull
    public String nomSport;
    @NotNull
    public String nomLigue;
    @NotNull
    public String nomTeam;
    @NotNull
    public String nom;
    @NotNull
    public String prenom;
    @NotNull
    public int number;
}
