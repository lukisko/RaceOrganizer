package com.example.raceorganizer.Ui.RaceInfo;

import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.LiveData.Checkpoint.CheckpointsLiveData;

import com.example.raceorganizer.Data.LiveData.Race.RaceLiveData;
import com.example.raceorganizer.Repository.CheckpointRepository;
import com.example.raceorganizer.Repository.RaceRepository;

public class RaceInfoViewModel extends ViewModel {
    private RaceRepository races;
    private CheckpointRepository checkpoint;

    public RaceInfoViewModel(){
        checkpoint = CheckpointRepository.getInstance();
        races = RaceRepository.getInstance();
    }

    public void init(String id,String user) {
        checkpoint.init("",id);
        races.init(id, user);
    }

    public RaceLiveData getRace(){
        return races.getRace();
    }

    public CheckpointsLiveData getCheckpoints(){
        return checkpoint.getCheckpoints();
    }
}
