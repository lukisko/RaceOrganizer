package com.example.raceorganizer.Data.Model;

import java.util.ArrayList;

public class Checkpoint {
    private String id;
    private String name;
    private String info;
    private int totalPoints;
    private int pointsReceived;
    private String raceId;
    private ArrayList<String> moderators;

    public Checkpoint() {
        moderators = new ArrayList<>();
    }

    public Checkpoint(String name) {
        this.name = name;
        this.info = "7/10";
        this.moderators = new ArrayList<>();
    }

    public Checkpoint(String id, String name, int totalPoints, int pointsReceived, String raceId) {
        this.id = id;
        this.name = name;
        this.totalPoints = totalPoints;
        this.pointsReceived = pointsReceived;
        this.raceId = raceId;
    }

    public void setRaceId(String raceId) {
        this.raceId = raceId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPointsReceived(int pointsReceived) {
        this.pointsReceived = pointsReceived;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getModerators() {
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

    public void setModerators(ArrayList<String> moderators) {
        this.moderators = moderators;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }


    public void setInfo(String info) {
        this.info = info;
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
