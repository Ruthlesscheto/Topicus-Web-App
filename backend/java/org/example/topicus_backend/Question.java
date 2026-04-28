package org.example.topicus_backend;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.Date;
import java.util.HashSet;
import model.ClassObject;
import model.Document;


@Path("/question")
public class Question {
    @GET
    //@Path("/{className}")
    @Produces(MediaType.APPLICATION_JSON)
    public HashSet<Document> getAllQuestionsInDocument(/*@PathParam("className") String className*/)
    {
        HashSet<Document> documents=new HashSet<>();
        documents.add(new Document(123, new Date(2005,2,3),new ClassObject("2B")));
        return documents;
    }


}
