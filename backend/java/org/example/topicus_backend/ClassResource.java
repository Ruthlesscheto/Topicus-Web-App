package org.example.topicus_backend;

import DatabaseConnection.DBConnection;
import exceptions.db.DBConnectionException;
import exceptions.db.DBQueryException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.HashSet;
import model.ClassObject;

@Path("/classes")
public class ClassResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HashSet<ClassObject> allClasses()
    {
        HashSet<ClassObject> classes = new HashSet<>();

        try {
            classes= DBConnection.getINSTANCE().allClasses();
        } catch (DBConnectionException | DBQueryException e) {
            System.out.println(e.getMessage());
        }

        return classes;
    }
}
