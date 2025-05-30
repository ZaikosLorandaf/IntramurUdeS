package ca.usherbrooke.fgen.api.service.postAdd;

import io.smallrye.common.constraint.NotNull;

import java.util.Date;

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
