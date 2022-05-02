package com.example.raceorganizer.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.raceorganizer.Data.Model.Race;

import java.util.ArrayList;

public class RaceRepository {
    private static RaceRepository instance;

    private MutableLiveData<ArrayList<Race>> races;

    private RaceRepository(){
        races = new MutableLiveData<>();
        ArrayList<Race> value = new ArrayList<>();
        for(int x = 0; x < 5; x++) {
            value.add(new Race("telovichovna jednota"));
            value.add(new Race("marin marathon"));
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

    public void insert(Race race) {
        ArrayList<Race> value = races.getValue();
        value.add(race);
        races.setValue(value);
    }

    public void deleteRace(){

    }

}
