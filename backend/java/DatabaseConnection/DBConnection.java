package DatabaseConnection;

import exceptions.db.DBConnectionException;
import exceptions.db.DBQueryException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import model.*;

public class DBConnection {
    private String url = "jdbc:postgresql://bronto.ewi.utwente.nl:5432/dab_di2425-2b_280";
    private String user = "dab_di2425-2b_280";
    private String password = "SgSSfbmOWGfBNsdG";

    private static DBConnection INSTANCE=null;

    public static DBConnection getINSTANCE() {
        if(INSTANCE==null)
        {
            INSTANCE=new DBConnection();
        }

        return INSTANCE;
    }

    private DBConnection()
    {
    }

    private Connection connect() throws DBConnectionException {

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Successful Connection");

            return connection;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new DBConnectionException(e.getMessage());
        }
    }

    public boolean login(String username, String hashedPassword) throws DBConnectionException,
            DBQueryException
    {
            Connection connection= connect();

            String query="select count(id) from editor where username=? and password =?;";
            try
            {
                PreparedStatement prepStatement= connection.prepareStatement(query);
                prepStatement.setString(1,username);
                prepStatement.setString(2,hashedPassword);

                ResultSet result=prepStatement.executeQuery();
                result.next();
                int status= result.getInt(1);

                return status==1;
            }
            catch (SQLException e) {
                throw new DBQueryException("Login",e.getMessage());
            }
    }

    public HashSet<ClassObject> allClasses() throws DBConnectionException {
        HashSet<ClassObject> classes=new HashSet<>();
            Connection connection= connect();
            try{
            String query="select class_name from class;\n";
            PreparedStatement prepStatement= connection.prepareStatement(query);
            ResultSet result= prepStatement.executeQuery();


            // Continue return all classes
            while (result.next())
            {
                String className= result.getString(1);
                classes.add(new ClassObject(className));
            }
            return classes;
        } catch (SQLException e) {
            throw new DBQueryException("All classes",e.getMessage());
        }
    }
/*@GET
@Path("/students/{classname}")
@Produces(MediaType.APPLICATION_JSON)
    public HashSet<Student> studentsInClass(@PathParam("classname") String className)
    {
        ClassObject classObject=new ClassObject(className);
        HashSet<Student> students=new HashSet<>();
        try {
            Connection connection= connect();

            String query="select first_name,last_name from student\n" +
                    "join class on class.id=student.class_id\n" + "where class.class_name=?;";
            PreparedStatement prepStatement= connection.prepareStatement(query);
            prepStatement.setString(1,className);

            ResultSet result= prepStatement.executeQuery();


            while(result.next())
            {
                String firstName= result.getString("first_name");
                String lastName= result.getString("last_name");
                Student student=new Student(firstName,lastName,classObject);
                students.add(student);
            }
            return students;
            // Continue return all classes

        } catch (SQLException e) {
            System.out.println("Incorrect credentials");
            // Add custom exception
        }

        return students;
    }*/

    public HashSet<Document> documentsOfClass( String className) throws DBConnectionException,DBQueryException {
        ClassObject classObject=new ClassObject(className);
        HashSet<Document> documents=new HashSet<>();
            Connection connection= connect();
            try{
            String query="select d.id,d.date from document as d\n" +
                    "join class on class.id=d.class_id\n" + "where class.class_name=?;";
            PreparedStatement prepStatement= connection.prepareStatement(query);
            prepStatement.setString(1,className);

            ResultSet result= prepStatement.executeQuery();

            while(result.next())
            {
                int id= result.getInt("id");
                Date date=result.getDate("date");
                Document document=new Document(id,date,classObject);
                documents.add(document);
            }

            System.out.println(documents);

            return documents;

        } catch (SQLException e) {
            throw new DBQueryException("DocumentOfClass",e.getMessage());
        }
    }

    public HashSet<String> allQuestionsInDocument( String className) throws DBConnectionException ,DBQueryException{
        HashSet<String> questions = new HashSet<>();
            Connection connection=connect();
            try{
            String query =
                    "SELECT q.question " +
                            "FROM \"dab_di2425-2b_280\".\"question\" q " +
                            "JOIN \"dab_di2425-2b_280\".document d ON q.document_id = d.id " +
                            "WHERE d.class_name = ?;";


            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, className);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
               String question = resultSet.getString(1);
               questions.add(question);

            }
            System.out.println(questions);
            connection.close();
        } catch (SQLException e) {
            throw new DBQueryException("AllQuestionsInDocument",e.getMessage());
        }
        return questions;
    }

    public HashSet<Student> studentInQuestion( String question) throws DBConnectionException,DBQueryException {
        HashSet<Student> students = new HashSet<>();
            Connection connection=connect();


            try
            {
            String query =
                    "SELECT s.first_name_name, s.last_name, c.class_name " +
                            "FROM \"dab_di2425-2b_280\".\"student\" s " +
                            "JOIN \"dab_di2425-2b_280\".\"question\" q ON s.id = q.student_id " +
                             "JOIN \"dab_di2425-2b_280\".\"class\" c ON s.class_id = c.id " +
                            "WHERE q.question = ?;";


            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, question);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
               String firstName = resultSet.getString(1);
               String lastName = resultSet.getString(2);
               String className = resultSet.getString(3);
               ClassObject classObject = new ClassObject(className);
               Student student = new Student(firstName, lastName, classObject);
                students.add(student);
            }
            System.out.println(students);
            connection.close();


        } catch (SQLException e) {
            throw new DBQueryException("StudentInQuestion",e.getMessage());
        }
        return students;
    }


    public List<Integer> gradesForStudents( int studentId) throws DBConnectionException,DBQueryException {
        List<Integer> grades = new ArrayList<>();
            Connection connection=connect();
            try{
            String query =
                    "SELECT g.grade " +
                            "FROM \"dab_di2425-2b_280\".\"grade\" g ,\"dab_di2425-2b_280\".\"student\"s " +
                            "WHERE g.student_id = s.id AND s.id =?;";


            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int grade = resultSet.getInt(1);
                grades.add(grade);
            }
            connection.close();
            System.out.println(grades);


        } catch (SQLException e) {
            throw new DBQueryException("GradesForStudents",e.getMessage());
        }
        return grades;
    }

    public List<Integer> gradeBasedOnStudentAndSubject(String firstname,
                                                       String lastname,  String subject) throws DBConnectionException,DBQueryException {
        List<Integer> grades = new ArrayList<>();

            Connection connection=connect();
            try {
            String query =
                    "SELECT g.grade " +
                            "FROM \"dab_di2425-2b_280\".\"grade\" g " +
                            "JOIN \"dab_di2425-2b_280\".\"subject\" sb ON g.subject = sb.id " +
                            "JOIN \"dab_di2425-2b_280\".\"student\" st ON g.student_id = st.id " +
                            "WHERE st.first_name_name = ? AND st.last_name = ? AND sb.subject_name = ?;";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstname);
            statement.setString(2, lastname);
            statement.setString(3, subject);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int grade = resultSet.getInt(1);
                grades.add(grade);
            }
        connection.close();
            System.out.println(grades);
        } catch (SQLException e) {
            throw new DBQueryException("GradeBasedOnStudentAndSubject",e.getMessage());
        }
    return grades;
    }

    public List<Date> notificationsForEditor( String firstName, String lastName) throws DBConnectionException,DBQueryException
    {
        List<Date> notificationDates = new ArrayList<>();
            Connection connection=connect();
            try{
            String query =
                    "SELECT n.date_modified " +
                            "FROM \"dab_di2425-2b_280\".\"notification\" n " +
                            "JOIN \"dab_di2425-2b_280\".\"editor\" e ON n.modified_by_id = e.id " +
                            "WHERE e.first_name = ? AND e.last_name = ?;";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,firstName);
            statement.setString(2,lastName);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                Date date = resultSet.getDate(1);
                notificationDates.add(date);
            }
        connection.close();
            System.out.println(notificationDates);
        }
        catch (SQLException e) {
            throw new DBQueryException("NotificationsForEditor",e.getMessage());
        }
    return notificationDates;
    }

    public int addStudent( String firstName, String lastName, int classId) throws DBConnectionException,DBQueryException {
            Connection connection = connect();
            // First check if the class exists
            String checkClassQuery = "SELECT id FROM class WHERE id = ?;";
            try {
                PreparedStatement checkClass = connection.prepareStatement(checkClassQuery);
                checkClass.setInt(1, classId);
                try (ResultSet classResult = checkClass.executeQuery()) {
                    if (!classResult.next()) {
                        throw new SQLException("Class with ID " + classId + " does not exist");
                    }
                }
            }
            catch (SQLException e) {
                throw new DBQueryException("AddStudent",e.getMessage());
            }
            
            // Insert the student
            try{
            String query = "INSERT INTO student (first_name, last_name, class_id) VALUES (?, ?, ?) RETURNING id;";
             PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setInt(3, classId);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("id");
                    } else {
                        throw new SQLException("Failed to retrieve generated ID");
                    }
                }
            }
            catch (SQLException e) {
                throw new DBQueryException("AddStudent- INSERT",e.getMessage());
            }
    }
    
    public static void main(String[] args) {
        DBConnection dbConnection=new DBConnection();
        HashSet<Document> documents= null;
        try {
            documents = dbConnection.documentsOfClass("2B");
        } catch (DBConnectionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(documents.size());
    }
}
