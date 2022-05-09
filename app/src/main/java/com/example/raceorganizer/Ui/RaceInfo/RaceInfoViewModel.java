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


    public RaceLiveData getRace(String id){
        return races.getRace(id);
    }

    public CheckpointsLiveData getCheckpoints(String raceId){
        return checkpoint.getCheckpoints(raceId);
    }

    public void deleteRace(String id){
        races.deleteRace(id);
    }
}
