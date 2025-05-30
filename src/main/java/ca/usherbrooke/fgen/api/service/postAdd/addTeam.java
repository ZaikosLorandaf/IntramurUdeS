package ca.usherbrooke.fgen.api.service.postAdd;

import io.smallrye.common.constraint.NotNull;

public class addTeam {
    @NotNull
    public String nomLigue;
    @NotNull
    public String nomSport;
    @NotNull
    public String nomTeam;
}
