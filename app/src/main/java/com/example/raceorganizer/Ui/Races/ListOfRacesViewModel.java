package com.example.raceorganizer.Ui.Races;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.LiveData.Race.RacesLiveData;
import com.example.raceorganizer.Data.LiveData.User.LoggedInUserLiveData;
import com.example.raceorganizer.Data.LiveData.User.UserIdLiveData;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Repository.LoggedInUserRepository;
import com.example.raceorganizer.Repository.RaceRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ListOfRacesViewModel extends AndroidViewModel {

    private RaceRepository raceRepository;
    private LoggedInUserRepository userRepository;

    public ListOfRacesViewModel(Application app){
        super(app);
        raceRepository = RaceRepository.getInstance();
        userRepository = LoggedInUserRepository.getInstance(app);
    }

    public RacesLiveData getAllRaces(String user){
        return raceRepository.getAllRaces(user);
    }


    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public UserIdLiveData getUserById(String id){
        return userRepository.getUserById(id);
    }

    public LiveData<ArrayList<Race>> getRacesParticipant(){
        return raceRepository.getRacesParticipant();
    }
}
