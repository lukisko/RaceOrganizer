package com.example.raceorganizer.Ui.addCheckpoint;

import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.LiveData.Moderator.ModeratorsLiveData;
import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Repository.CheckpointRepository;
import com.example.raceorganizer.Repository.ModeratorRepositori;
import com.example.raceorganizer.Repository.RaceRepository;

import java.util.ArrayList;

public class AddCheckpointViewModel extends ViewModel {
    private CheckpointRepository checkpointRepository;
    private RaceRepository raceRepository;
    private ModeratorRepositori moderatorRepositori;

    public AddCheckpointViewModel(){
        checkpointRepository = CheckpointRepository.getInstance();
        raceRepository = RaceRepository.getInstance();
        moderatorRepositori = ModeratorRepositori.getInstance();
    }

    public void makeCheckpoint( Checkpoint checkpoint){
        checkpointRepository.addCheckpoint( checkpoint);
    }


}