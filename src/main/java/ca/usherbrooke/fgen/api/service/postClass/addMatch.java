package ca.usherbrooke.fgen.api.service.postClass;

import io.smallrye.common.constraint.NotNull;
public class addMatch {
    @NotNull
    public String sportName;
    @NotNull
    public String ligueName;
    @NotNull
    public String date;
    @NotNull
    public String teamName;
    @NotNull
    public String heureDebut;
    @NotNull
    public String heureFin;
}
