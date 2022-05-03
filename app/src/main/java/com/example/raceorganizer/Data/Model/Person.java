package com.example.raceorganizer.Data.Model;

public class Person {
    private String firstName;
    private String lastName;
    private int number;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumber() {
        return number;
    }
}
