package com.example.raceorganizer.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Race;

import java.util.ArrayList;
import java.util.Objects;

public class CheckpointRepository {
    private static CheckpointRepository instance;
    private static final Object lock = new Object();

    private MutableLiveData<ArrayList<Checkpoint>> checkpoints;

    private CheckpointRepository(){
        ArrayList<Checkpoint> myCheck = new ArrayList<>();
        checkpoints = new MutableLiveData<>(myCheck);
    }

    public static CheckpointRepository getInstance() {
        if (instance != null){
            return instance;
        }
        synchronized (lock){
            instance = new CheckpointRepository();
        }
        return instance;
    }

    public LiveData<ArrayList<Checkpoint>> getAllCheckpoints() {
        return checkpoints;
    }

    public LiveData<Checkpoint> getCheckpoint(String name){
        for (Checkpoint check: Objects.requireNonNull(checkpoints.getValue())) {
            if(check.getName().equals(name)){
                return new MutableLiveData<Checkpoint>(check);
            }
        }
        return null;
    }

    public void addCheckpoint(Race race, Checkpoint checkpoint){
        //nothing for now.
    }
}
