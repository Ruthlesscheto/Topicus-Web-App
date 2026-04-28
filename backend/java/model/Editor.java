package model;

public class Editor {
    private String firstName;
    private String lastName;
    private boolean role;
    private String username;
    private String password;

    public Editor(String firstName, String lastName, boolean role, String username, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isRole() {
        return role;
    }
}
