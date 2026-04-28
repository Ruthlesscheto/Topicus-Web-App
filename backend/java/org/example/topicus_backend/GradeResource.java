package org.example.topicus_backend;

import DatabaseConnection.DBConnection;
import exceptions.db.DBConnectionException;
import exceptions.db.DBQueryException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Path("/grade")
public class GradeResource {
    @GET
    @Path("/students/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> getGradesForStudents(@PathParam("studentId") int studentId)
    {
        List<Integer> grades=new ArrayList<>();

        try
        {
            grades=DBConnection.getINSTANCE().gradesForStudents(studentId);
        } catch (DBConnectionException | DBQueryException e) {
            System.out.println(e.getMessage());
        }
        return grades;
    }

    @GET
    @Path("/{firstname}/{lastname}/{subject}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> getGradeBasedOnStudentAndSubject(@PathParam("firstname") String firstname,@PathParam("lastname") String lastname,@PathParam("subject") String subject)
    {
        List<Integer> grades=new ArrayList<>();

        try
        {
            grades=DBConnection.getINSTANCE().gradeBasedOnStudentAndSubject(firstname,lastname,subject);
        } catch (DBConnectionException | DBQueryException e) {
            System.out.println(e.getMessage());
        }
        return grades;
    }
}
