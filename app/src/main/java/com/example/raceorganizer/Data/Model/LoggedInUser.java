package com.example.raceorganizer.Data.Model;

public class LoggedInUser {
    private String Id;
    private String nickName;
    private String password;
    private String firstName;
    private String lastName;

    public LoggedInUser(){}
    public LoggedInUser(String nickName, String password, String firstName, String lastName) {
        this.nickName = nickName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return Id;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
