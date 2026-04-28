package org.example.topicus_backend;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.annotations.Pos;

@Path("/login")
public class LoginResource {
    @POST
    @Path("/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean login(@PathParam("username") String username,@PathParam("password") String password)
    {
        return  false;
    }
}
