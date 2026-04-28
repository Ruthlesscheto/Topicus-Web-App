package model;

import java.util.Collection;
import java.util.HashSet;

public class ClassObject {
    private String name;
    final HashSet<Student> students=new HashSet<>();
    public ClassObject(String name)
    {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStudent(Student student)
    {
        students.add(student);
    }
    public void addAllStudents(Collection<Student> students)
    {
        this.students.addAll(students);
    }
    public Collection<Student> getStudents()
    {
        return new HashSet<>(students);
    }

}
