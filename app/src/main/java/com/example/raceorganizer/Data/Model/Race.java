package com.example.raceorganizer.Data.Model;

import java.time.LocalDate;
import java.util.Date;

public class Race {
    private String name;

    private Date date;
    private int startTime;
    private int endTime;
    private RaceType raceType;
    private int participantsAmount;

    public Race(String name) {
        this.name = name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setRaceType(RaceType raceType) {
        this.raceType = raceType;
    }

    public void setParticipantsAmount(int participantsAmount) {
        this.participantsAmount = participantsAmount;
    }

    public String getName() {
        return name;
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public int getParticipantsAmount() {
        return participantsAmount;
    }

}
