package ca.usherbrooke.fgen.api.service.postClass;

import io.smallrye.common.constraint.NotNull;

public class addPlayer {
    @NotNull
    public String nomLigue;
    @NotNull
    public String nomSport;
    @NotNull
    public String nomTeam;
    @NotNull
    public int number;
    @NotNull
    public String nom;
    @NotNull
    public String prenom;
}
