package ca.usherbrooke.fgen.api.service.objectServices;

import ca.usherbrooke.fgen.api.backend.Singleton.OGClass;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("/api/statistique")
public class StatistiqueService {
    @Inject
    OGClass ogClass;

    @Path("add")
    public String addStatistique()
    {
        return "add";
    }

    @Path("add")
    public String removeStatistique()
    {
        return "remove";
    }
}
