package ca.usherbrooke.fgen.api.service;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import ca.usherbrooke.fgen.api.backend.MaClasseOG;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MonAPI {

    @Inject
    MaClasseOG monService;

    @GET
    @Path("monendpoint")
    public String getMonMessage() {
        return monService.getMessage();
    }
}
