package ca.usherbrooke.fgen.api.service;

import org.eclipse.microprofile.jwt.JsonWebToken;
import javax.inject.Inject;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.quarkus.security.identity.SecurityIdentity;

@Path("/api/auth")
public class Auth {


    @Inject
    SecurityIdentity identity;

    @GET
    @RolesAllowed("User")
    @Path("/me")
    public User getAuth() {
        return new User(identity);
    }

    private static class User {
        private final String userName;
        User(SecurityIdentity identity) {
            if (identity.isAnonymous()) {
                throw new IllegalArgumentException("Anonymous users are not allowed");
            }
            this.userName = identity.getPrincipal().getName();
        }

        public String getUserName() {
            return userName;
        }
    }

}
