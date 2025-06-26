package ca.usherbrooke.fgen.api.service.postClass;

import io.smallrye.common.constraint.NotNull;

public class removeMatch {
    @NotNull
    public String sport;
    @NotNull
    public String ligue;
    @NotNull
    public String date;
    @NotNull
    public String team1;
    @NotNull
    public String team2;
}
