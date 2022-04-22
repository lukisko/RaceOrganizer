package com.example.raceorganizer.Model;

import java.util.Date;

public class Race {
    private String name;
    private Date start;
    private Date end;
    private RaceType raceType;
    private int participantsAmount;

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

    public String getName() {
        return name;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public int getParticipantsAmount() {
        return participantsAmount;
    }

}
