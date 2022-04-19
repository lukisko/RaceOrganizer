package com.example.raceorganizer.ui2.checkpointList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.domain.Checkpoint;

import java.util.ArrayList;

public class CheckpointListPViewModel extends ViewModel {
    //TODO make logic for checkpoint list view model
    private MutableLiveData<ArrayList<Checkpoint>> checkpoints;

    public CheckpointListPViewModel(){
        checkpoints = new MutableLiveData<>();
        ArrayList<Checkpoint> newCheckpoints = checkpoints.getValue();
        newCheckpoints.add(new Checkpoint("testing point","7/10"));
        newCheckpoints.add(new Checkpoint("testing point2","6/10"));
        newCheckpoints.add(new Checkpoint("testing point3","8/10"));
        newCheckpoints.add(new Checkpoint("testing point4","2/10"));
        checkpoints.postValue(newCheckpoints);
    }

    public LiveData<ArrayList<Checkpoint>> getCheckpoints() {
        return checkpoints;
    }
}
