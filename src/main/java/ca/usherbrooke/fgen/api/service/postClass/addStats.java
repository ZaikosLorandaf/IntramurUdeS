package ca.usherbrooke.fgen.api.service.postClass;

import io.smallrye.common.constraint.NotNull;

public class addStats {
    @NotNull
    public String sportName;
    @NotNull
    public String ligueName;
    @NotNull
    public String teamName;
    @NotNull
    public String key;
    @NotNull
    public String value;
}
