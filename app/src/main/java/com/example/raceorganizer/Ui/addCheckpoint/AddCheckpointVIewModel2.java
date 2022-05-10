package com.example.raceorganizer.Ui.addCheckpoint;

import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Repository.CheckpointRepository;
import com.example.raceorganizer.Repository.RaceRepository;

public class AddCheckpointVIewModel2 extends ViewModel {
    private CheckpointRepository checkpointRepository;
    private RaceRepository raceRepository;

    public AddCheckpointVIewModel2(){
        checkpointRepository = CheckpointRepository.getInstance();
        raceRepository = RaceRepository.getInstance();
    }

    public void makeCheckpoint(String raceName, Checkpoint checkpoint){
        checkpointRepository.addCheckpoint(/*raceRepository.getRace(raceName).getValue(),*/ checkpoint);
    }
}