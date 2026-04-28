package org.example.topicus_backend;

import DatabaseConnection.DBConnection;
import exceptions.db.DBConnectionException;
import exceptions.db.DBQueryException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Path("/notification")
public class Notification {
    @GET
    @Path("/{firstname}/{lastname}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Date> getNotificationsForEditor(@PathParam("firstname") String firstname, @PathParam("lastname") String lastname)
    {
        List<Date> dates=new ArrayList<>();

        try {
            dates= DBConnection.getINSTANCE().notificationsForEditor(firstname,lastname);
        } catch (DBConnectionException | DBQueryException e) {
            System.out.println(e.getMessage());
        }
        return dates;
    }
}
