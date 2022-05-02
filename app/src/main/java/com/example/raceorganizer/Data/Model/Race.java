package com.example.raceorganizer.Data.Model;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;


public class Race {
    private String name;
    private Date start;
    private Date end;
    private RaceType raceType;
    private ArrayList<Checkpoint> checkpoints;
    private int participantsAmount;

    public Race(String name, Date start, Date end, RaceType raceType, ArrayList<Checkpoint> checkpoints, int participantsAmount) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.raceType = raceType;
        this.checkpoints = checkpoints;
        this.participantsAmount = participantsAmount;
    }

    public Race(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setRaceType(RaceType raceType) {
        this.raceType = raceType;
    }

    public void setParticipantsAmount(int participantsAmount) {
        this.participantsAmount = participantsAmount;
    }

    public void setCheckpoints(ArrayList<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
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

    public RaceType getRaceType() {
        return raceType;
    }

    public int getParticipantsAmount() {
        return participantsAmount;
    }

    public ArrayList<Checkpoint> getCheckpoints() {
        return checkpoints;
    }
}
