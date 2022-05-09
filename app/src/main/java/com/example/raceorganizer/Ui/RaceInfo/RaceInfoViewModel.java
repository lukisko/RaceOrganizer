package com.example.raceorganizer.Ui.RaceInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.LiveData.CheckpointLiveData;

import com.example.raceorganizer.Data.LiveData.RaceLiveData;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Repository.CheckpointRepository;
import com.example.raceorganizer.Repository.RaceRepository;

public class RaceInfoViewModel extends ViewModel {
    private RaceRepository races;
    private CheckpointRepository checkpoint;

    public RaceInfoViewModel(){
        checkpoint = CheckpointRepository.getInstance();
        races = RaceRepository.getInstance();
    }

    public void init(String id) {
        checkpoint.init();
        races.init(id);
    }

    public RaceLiveData getRace(){
        return races.getRace();
    }

    public CheckpointLiveData getCheckpoints(){
        return checkpoint.getCheckpoints();
    }
}
