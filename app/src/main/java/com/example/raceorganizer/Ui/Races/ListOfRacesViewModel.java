package com.example.raceorganizer.Ui.Races;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.Dao.CheckpointDao;
import com.example.raceorganizer.Data.LiveData.Checkpoint.CheckpointsLiveData;
import com.example.raceorganizer.Data.LiveData.Participant.ParticipantLiveData;
import com.example.raceorganizer.Data.LiveData.Race.RacesLiveData;
import com.example.raceorganizer.Data.LiveData.User.AuthenticationLiveData;
import com.example.raceorganizer.Data.LiveData.User.UserIdLiveData;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Repository.AuthenticationRepository;
import com.example.raceorganizer.Repository.CheckpointRepository;
import com.example.raceorganizer.Repository.ParticipantRepository;
import com.example.raceorganizer.Repository.RaceRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ListOfRacesViewModel extends AndroidViewModel {

    private RaceRepository raceRepository;
    private ParticipantRepository participantRepository;
    private AuthenticationRepository userRepository;
    private CheckpointRepository checkpointRepository;

    public ListOfRacesViewModel(Application app){
        super(app);
        raceRepository = RaceRepository.getInstance();
        userRepository = AuthenticationRepository.getInstance(app);
        participantRepository = ParticipantRepository.getInstance();
        checkpointRepository = CheckpointRepository.getInstance();
    }

    public RacesLiveData getAllRaces(String user){
        return raceRepository.getAllRaces(user);
    }


    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }



    public ParticipantLiveData getParticipant(String id){
        return participantRepository.getParticipant(id);
    }

    public LiveData<ArrayList<Race>> getRaces(ArrayList<String> list){
        return raceRepository.getRaces(list);
    }

    public CheckpointsLiveData getCheckpointsByModerator(String moderatorId){
       return checkpointRepository.getCheckpointsByModerator(moderatorId);
    }
}
