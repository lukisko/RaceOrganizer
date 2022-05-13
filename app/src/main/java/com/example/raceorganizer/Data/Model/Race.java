package com.example.raceorganizer.Data.Model;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;


public class Race {
    private String id;
    private String name;
    private Date start;
    private Date end;
    private String raceType;
    private String raceOwner;
    private ArrayList<Checkpoint> checkpoints;
    private ArrayList<Participant> participants;

    public Race(){
        checkpoints = new ArrayList<>();
        participants = new ArrayList<>();
    }
    public Race(String id,String name, Date start, Date end, String raceType, String raceOwner) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.raceType = raceType;
        this.checkpoints = new ArrayList<>();
        this.raceOwner = raceOwner;
        this.participants = new ArrayList<>();

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setRaceType(String raceType) {
        this.raceType = raceType;
    }

    public void setRaceOwner(String raceOwner) {
        this.raceOwner = raceOwner;
    }

    public void setCheckpoints(ArrayList<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStart() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat ft = new SimpleDateFormat ("hh:mm");
        return ft.format(start);
    }

    public String getEnd() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat ft = new SimpleDateFormat ("hh:mm");
        return ft.format(end);
    }

    public String getDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");
        return ft.format(start);
    }

    public String getRaceType() {
        return raceType;
    }

    public int getParticipantsAmount() {
        return participants.size();
    }

    public ArrayList<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public ArrayList<RegisteredUser> getModerators() {
        ArrayList<RegisteredUser> result = new ArrayList<>();
        for (Checkpoint ch:checkpoints) {
            result.addAll(ch.getModerators());
        }
        return result;
    }

    public String getRaceOwner() {
        return raceOwner;
    }

    @Override
    public String toString() {
        return "Race{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", raceOwner=" + raceOwner +
                ", raceType=" + raceType +
                ", checkpoints=" + checkpoints +
                ", participants=" + participants +
                '}';
    }
}
