package com.example.raceorganizer.Ui.moderatorCheckpoint;

import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.LiveData.Checkpoint.CheckpointsLiveData;
import com.example.raceorganizer.Repository.CheckpointRepository;

public class ModeratorCheckpointViewModel extends ViewModel {

    public CheckpointRepository checkpointRepository;

    public ModeratorCheckpointViewModel() {
        checkpointRepository = CheckpointRepository.getInstance();
    }


    public CheckpointsLiveData getCheckpoints(String raceId){
        return checkpointRepository.getCheckpoints(raceId);
    }

}
