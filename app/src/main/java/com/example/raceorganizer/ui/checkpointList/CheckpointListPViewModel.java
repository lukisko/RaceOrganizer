package com.example.raceorganizer.ui.checkpointList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.Model.Checkpoint;

import java.util.ArrayList;

public class CheckpointListPViewModel extends ViewModel {
    //TODO make logic for checkpoint list view model
    private MutableLiveData<ArrayList<Checkpoint>> checkpoints;

    public CheckpointListPViewModel(){
        checkpoints = new MutableLiveData<>();
        ArrayList<Checkpoint> newCheckpoints = checkpoints.getValue();
        newCheckpoints.add(new Checkpoint("testing point"));
        newCheckpoints.add(new Checkpoint("testing point2"));
        newCheckpoints.add(new Checkpoint("testing point3"));
        newCheckpoints.add(new Checkpoint("testing point4"));
        checkpoints.postValue(newCheckpoints);
    }

    public LiveData<ArrayList<Checkpoint>> getCheckpoints() {
        return checkpoints;
    }
}
