package com.example.raceorganizer.Data.Model;

import java.util.ArrayList;

public class Participant {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private int number;
    private int points;
    private int totalTime;
    private ArrayList<Checkpoint> checkpoints;
    private ArrayList<Integer> raceIds;

    public Participant(){}
    public Participant(String id,String firstName, String lastName, int age, int number, int points, int totalTime) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.number = number;
        this.points = points;
        this.totalTime = totalTime;
        checkpoints = new ArrayList<>();
        raceIds = new ArrayList<>();
    }

    public void addCheckpoint(Checkpoint checkpoint){
        checkpoints.add(checkpoint);
    }

    public String getId() {
        return id;
    }

    public ArrayList<Integer> getRaceIds() {
        return raceIds;
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
