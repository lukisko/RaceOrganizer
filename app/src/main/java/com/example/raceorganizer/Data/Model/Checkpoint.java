package com.example.raceorganizer.Data.Model;

import java.util.ArrayList;

public class Checkpoint {
    private String name;
    private String info;
    private int totalPoints;
    private int pointsReceived;
    private ArrayList<LoggedInUser> moderators;

    public Checkpoint(){}
    public Checkpoint(String name){
        this.name = name;
        this.info = "7/10";
        this.moderators = new ArrayList<>();
    }

    public Checkpoint(String name, int totalPoints, int pointsReceived) {
        this.name = name;
        this.totalPoints = totalPoints;
        this.pointsReceived = pointsReceived;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public ArrayList<LoggedInUser> getModerators() {
        return moderators;
    }
}
