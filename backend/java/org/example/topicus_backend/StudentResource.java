package org.example.topicus_backend;

import DatabaseConnection.DBConnection;
import exceptions.db.DBConnectionException;
import exceptions.db.DBQueryException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.HashSet;
import model.Student;

@Path("/student")
public class StudentResource {
    @GET
    @Path("/{question}")
    @Produces(MediaType.APPLICATION_JSON)
    public HashSet<Student> getStudentInQuestion(@PathParam("question") String question) {
        HashSet<Student> students=new HashSet<>();

        try {
            students= DBConnection.getINSTANCE().studentInQuestion(question);
        } catch (DBConnectionException | DBQueryException e) {
            System.out.println(e.getMessage());
        }

        return students;
    }

    @PUT
    @Path("/{firstname}/{lastname}/{classID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer addStudent(@PathParam("firstname") String firstname,
                          @PathParam("lastname") String lastname, @PathParam("classID") int classID)
    {
        Integer state = 0;
        try
        {
             state=DBConnection.getINSTANCE().addStudent(firstname,lastname,classID);
        }
        catch (DBConnectionException|DBQueryException e)
        {
            System.out.println(e.getMessage());
        }
        return state;
    }

//    @GET
//    @Path("/{classname}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public HashSet<Student> studentsInClass(@PathParam("classname") String className)
//    {
//        return null;
//    }
}
