package com.example.raceorganizer.Ui.checkpointList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.LiveData.Race.RaceLiveData;
import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Repository.RaceRepository;

import java.util.ArrayList;

public class CheckpointListPViewModel extends ViewModel {
    //TODO make logic for checkpoint list view model
    private MutableLiveData<ArrayList<Checkpoint>> checkpoints;
    private RaceRepository raceRepository;

    public CheckpointListPViewModel(){
        checkpoints = new MutableLiveData<>(new ArrayList<>());
        ArrayList<Checkpoint> newCheckpoints = checkpoints.getValue();
        newCheckpoints.add(new Checkpoint("testing point"));
        newCheckpoints.add(new Checkpoint("testing point2"));
        newCheckpoints.add(new Checkpoint("testing point3"));
        newCheckpoints.add(new Checkpoint("testing point4"));
        checkpoints.postValue(newCheckpoints);

        raceRepository = RaceRepository.getInstance();
    }

    public LiveData<ArrayList<Checkpoint>> getCheckpoints() {
        return checkpoints;
    }

    public RaceLiveData getRace(String id){
        //checkpoints.postValue(race.getValue().getCheckpoints());
        return raceRepository.getRace(id);

    }
}
