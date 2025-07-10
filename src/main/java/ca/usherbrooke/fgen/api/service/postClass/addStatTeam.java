package ca.usherbrooke.fgen.api.service.postClass;

import io.smallrye.common.constraint.NotNull;

public class addStatTeam {
    @NotNull
    public String sport;
    @NotNull
    public String ligue;
    @NotNull
    public String team;
    @NotNull
    public String key;
    @NotNull
    public String value;
}
