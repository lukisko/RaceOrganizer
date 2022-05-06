package com.example.raceorganizer.Ui.RaceInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.LiveData.CheckpointLiveData;

import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Repository.CheckpointRepository;
import com.example.raceorganizer.Repository.RaceRepository;

public class RaceInfoViewModel extends ViewModel {
    private RaceRepository repository;
    private CheckpointRepository checkpoint;

    public RaceInfoViewModel(){
        checkpoint = CheckpointRepository.getInstance();
    }

    public void init() {
        checkpoint.init();
    }

    public LiveData<Race> getRace(String name){
        return null;
    }

    public CheckpointLiveData getCheckpoints(){
        return checkpoint.getCheckpoints();
    }
}
