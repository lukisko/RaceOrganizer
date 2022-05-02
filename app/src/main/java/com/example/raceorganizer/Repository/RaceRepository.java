package com.example.raceorganizer.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Data.Model.RaceType;


import java.util.ArrayList;


import java.util.Date;
import java.util.Objects;

public class RaceRepository {
    private static RaceRepository instance;

    private MutableLiveData<ArrayList<Race>> races;

    private RaceRepository(){
        races = new MutableLiveData<>();
        ArrayList<Race> value = new ArrayList<>();
        ArrayList<Checkpoint> checkpoints = new ArrayList<>();
        checkpoints.add(new Checkpoint("asd"));
        checkpoints.add(new Checkpoint("a"));
        Date data = new Date();
        for(int x = 0; x < 5; x++) {
            value.add(new Race("telovichovna jednota", data,data, RaceType.Marathon,checkpoints ,12312));
            value.add(new Race("martin", data,data, RaceType.Skiing,checkpoints ,12312));
        }
        races.setValue(value);
    }

    public static RaceRepository getInstance(){
        if(instance == null)
            instance = new RaceRepository();

        return instance;
    }

    public LiveData<ArrayList<Race>> getAllRaces(){
        return races;
    }

    public LiveData<Race> getRace(String name){
        for (Race race: Objects.requireNonNull(races.getValue())) {
            if(race.getName().equals(name)){
                return new MutableLiveData<Race>(race);
            }
        }
        return null;
    }

    public void insert(Race race) {
        ArrayList<Race> value = races.getValue();
        value.add(race);
        races.setValue(value);
    }

    public void deleteRace(){

    }

}
