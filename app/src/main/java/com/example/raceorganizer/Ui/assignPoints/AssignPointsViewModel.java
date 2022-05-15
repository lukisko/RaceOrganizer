package com.example.raceorganizer.Ui.assignPoints;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Repository.AuthenticationRepository;
import com.example.raceorganizer.Repository.ParticipantRepository;
import com.example.raceorganizer.Repository.RaceRepository;
import com.example.raceorganizer.Ui.addRaceFragment.AddRaceViewModel;

public class AssignPointsViewModel extends ViewModel {


    private static AssignPointsViewModel instance;
    private ParticipantRepository participantRepository;

    private AssignPointsViewModel() {
        this.participantRepository = ParticipantRepository.getInstance();

    }

    public static synchronized AssignPointsViewModel getInstance() {
        if (instance == null)
            instance = new AssignPointsViewModel();
        return instance;
    }

    private void assignPoints(String participantId, String checkpointId, String points) {
        participantRepository.addCheckpoint(participantId, checkpointId, points);
    }

}
