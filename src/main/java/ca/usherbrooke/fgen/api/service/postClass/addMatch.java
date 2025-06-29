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
    public String equipe1;
    @NotNull
    public String equipe2;
    @NotNull
    public String heure_debut;
    @NotNull
    public String heure_fin;
}
