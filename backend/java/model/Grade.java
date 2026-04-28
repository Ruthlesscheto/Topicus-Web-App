package model;

public class Grade {
private SubjectObject subjectObject;
private double grade;
private Student student;

public Grade(SubjectObject subject, double grade, Student student)
{
    this.student = student;
    this.subjectObject = subject;
    this.grade = grade;
}

    public double getGrade() {
        return grade;
    }

    public Student getStudent() {
        return student;
    }

    public SubjectObject getSubjectObject() {
        return subjectObject;
    }
}
