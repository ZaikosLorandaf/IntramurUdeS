package ca.usherbrooke.fgen.api.service.postClass;

import io.smallrye.common.constraint.NotNull;

public class addLeague {
    @NotNull
    public String nom_sport;
    @NotNull
    public String nom;
    @NotNull
    public String date_debut;
    @NotNull
    public String date_fin;

}
