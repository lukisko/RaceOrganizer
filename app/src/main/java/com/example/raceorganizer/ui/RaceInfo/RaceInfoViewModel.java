package com.example.raceorganizer.ui.RaceInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.CheckpointPersistence.CheckpointLiveData;
import com.example.raceorganizer.Data.CheckpointPersistence.CheckpointRepository;
import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Repository.RaceRepository;

public class RaceInfoViewModel extends ViewModel {
    private RaceRepository repository;
    private CheckpointRepository checkpoint;

    public RaceInfoViewModel(){
        repository = RaceRepository.getInstance();
        checkpoint = CheckpointRepository.getInstance();
    }

    public void init() {
        checkpoint.init();
    }

    public LiveData<Race> getRace(String name){
        return repository.getRace(name);
    }

    public CheckpointLiveData getCheckpoints(){
        return checkpoint.getCheckpoints();
    }
}
