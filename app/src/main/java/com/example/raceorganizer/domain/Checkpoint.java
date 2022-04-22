package com.example.raceorganizer.domain;

public class Checkpoint {
    private String name;
    private String info;

    public Checkpoint(String name, String info){
        this.name = name;
        this.info = name;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
}
