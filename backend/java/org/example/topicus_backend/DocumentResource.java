package org.example.topicus_backend;

import DatabaseConnection.DBConnection;
import exceptions.db.DBConnectionException;
import exceptions.db.DBQueryException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.HashSet;
import javax.print.Doc;
import javax.print.attribute.standard.Media;
import model.Document;

@Path("/documents")
public class DocumentResource {
    @GET
    @Path("/{className}")
    @Produces(MediaType.APPLICATION_JSON)
    public HashSet<Document>  documentOfClass(@PathParam("className")String className)
    {
        HashSet<Document> documents=new HashSet<>();
        try {
            documents= DBConnection.getINSTANCE().documentsOfClass(className);
        } catch (DBConnectionException e) {
            System.out.println(e.getMessage());
        }
        catch (DBQueryException e)
        {
            System.out.println(e.getMessage());
        }
        return documents;
    }
}
