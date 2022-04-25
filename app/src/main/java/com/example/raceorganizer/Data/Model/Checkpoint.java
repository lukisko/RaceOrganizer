package com.example.raceorganizer.Data.Model;

public class Checkpoint {
    private String name;
    private String info;
    private int totalPoints;
    private int pointsReceived;
    private boolean visited;

    public Checkpoint(String name){
        this.name = name;
        this.info = "7/10";
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
}
