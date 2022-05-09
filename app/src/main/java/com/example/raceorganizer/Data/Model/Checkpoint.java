package com.example.raceorganizer.Data.Model;

import java.util.ArrayList;

public class Checkpoint {
    private String name;
    private String info;
    private int totalPoints;
    private int pointsReceived;
    private ArrayList<RegisteredUser> moderators;

    public Checkpoint(String name){
        this.name = name;
        this.info = "7/10";
        this.moderators = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public ArrayList<RegisteredUser> getModerators() {
        return moderators;
    }
}
