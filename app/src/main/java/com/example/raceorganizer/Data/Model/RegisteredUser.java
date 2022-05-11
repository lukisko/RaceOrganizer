package com.example.raceorganizer.Data.Model;

public class RegisteredUser {
    private String id;
    private String username;
    private String firstName;
    private String lastName;

    public RegisteredUser(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
//    public RegisteredUser(String username, String firstName, String lastName) {
//        this.username = username;
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }

    public String getId() {
        return id;
    }

    public String getNickName() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

