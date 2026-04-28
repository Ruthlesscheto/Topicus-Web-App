package model;

public class Student {
    private String firstName;
    private String lastName;
    private ClassObject classObject;

    public Student(String firstName,String lastName,ClassObject classObject)
    {
        this.firstName=firstName;
        this.lastName=lastName;
        this.classObject=classObject;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}


