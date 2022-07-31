package com.example.raceorganizer.Data.Model;

import com.google.firebase.auth.FirebaseUser;

public class User  {

    private String id;
    private String  username;
    private String password;
    private String firstName;
    private String lastName;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.firstName=null;
        this.lastName =null;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
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
