package model;

public class Question {

    private Document document;
    private String question;
    private String answer;
    private String action;
    private boolean actionDone;
    private Student student;
    private SubjectObject subjectObject;


    public Question(Document document, String question, String answer, String action, boolean actionDone, Student
            student, SubjectObject subjectObject)
    {
        this.action = action;
        this.document = document;
        this.question = question;
        this.answer = answer;
        this.actionDone = actionDone;
        this.subjectObject = subjectObject;
        this.student = student;
    }

    public SubjectObject getSubjectObject() {
        return subjectObject;
    }

    public Student getStudent() {
        return student;
    }

    public Document getDocument() {
        return document;
    }

    public String getAction() {
        return action;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }
}



