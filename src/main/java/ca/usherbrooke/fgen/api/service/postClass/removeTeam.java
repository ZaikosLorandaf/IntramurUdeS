package ca.usherbrooke.fgen.api.service.postClass;

import io.smallrye.common.constraint.NotNull;

public class removeTeam {
    @NotNull
    public String sportName;
    @NotNull
    public String leagueName;
    @NotNull
    public  String teamName;
}
