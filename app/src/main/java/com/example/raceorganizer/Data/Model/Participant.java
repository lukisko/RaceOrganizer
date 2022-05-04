package com.example.raceorganizer.Data.Model;

import java.util.ArrayList;

public class Participant {
    private String firstName;
    private String lastName;
    private int age;
    private int number;
    private int points;
    private int totalTime;
    private ArrayList<Checkpoint> checkpoints;

    public Participant(String firstName, String lastName, int age, int number, int points, int totalTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.number = number;
        this.points = points;
        this.totalTime = totalTime;
        checkpoints = new ArrayList<>();
    }

    public void addCheckpoint(Checkpoint checkpoint){
        checkpoints.add(checkpoint);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getNumber() {
        return number;
    }

    public int getPoints() {
        return points;
    }

    public int getTotalTime() {
        return totalTime;
    }
}
