package ca.usherbrooke.fgen.api.service;

import ca.usherbrooke.fgen.api.backend.OGClass;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;

@Path("/api/dashboard")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetDashboard {
    @Inject
    OGClass monService;

    @GET
    @Path("nothing")
    public String nothing() {
        return "---";
    }

    @GET
    @Path("equipes")
    public String getEquipesData(
            @QueryParam("sport") String nomSport,
             @QueryParam("ligue") String nomLigue) {
        return monService.getEquipesData(nomSport, nomLigue);
    }
}
