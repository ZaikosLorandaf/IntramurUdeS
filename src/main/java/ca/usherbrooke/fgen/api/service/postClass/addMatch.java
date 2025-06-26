package ca.usherbrooke.fgen.api.service.postClass;

import io.smallrye.common.constraint.NotNull;
public class addMatch {
    @NotNull
    public String sport;
    @NotNull
    public String ligue;
    @NotNull
    public String date;
    @NotNull
    public String equipes;
    @NotNull
    public String heure_debut;
    @NotNull
    public String heure_fin;
}
