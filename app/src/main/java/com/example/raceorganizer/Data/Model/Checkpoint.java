package com.example.raceorganizer.Data.Model;

import java.util.ArrayList;

public class Checkpoint {
    private String id;
    private String name;
    private String info;
    private int totalPoints;
    private int pointsReceived;
    private String raceId;
    private ArrayList<RegisteredUser> moderators;

    public Checkpoint(){}
    public Checkpoint(String name){
        this.name = name;
        this.info = "7/10";
        this.moderators = new ArrayList<>();
    }

    public Checkpoint(String id, String name, int totalPoints, int pointsReceived) {
        this.id = id;
        this.name = name;
        this.totalPoints = totalPoints;
        this.pointsReceived = pointsReceived;
    }
    public Checkpoint(String id, String name, int totalPoints, int pointsReceived,String raceId) {
        this.id = id;
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

    public ArrayList<RegisteredUser> getModerators() {
        return moderators;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getPointsReceived() {
        return pointsReceived;
    }

    public String getRaceId() {
        return raceId;
    }

    @Override
    public String toString() {
        return "Checkpoint{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", totalPoints=" + totalPoints +
                ", pointsReceived=" + pointsReceived +
                ", raceId='" + raceId + '\'' +
                ", moderators=" + moderators +
                '}';
    }
}
