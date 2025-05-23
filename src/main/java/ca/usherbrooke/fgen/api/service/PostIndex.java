package ca.usherbrooke.fgen.api.service;

import ca.usherbrooke.fgen.api.backend.OGClass;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;

@Path("/api/index")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostIndex {

    @Inject
    OGClass monService;

    @GET
    @Path("nothing")
    public String nothing() {
        return "---";
    }
}
