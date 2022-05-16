package com.example.raceorganizer.Ui.addRaceFragment;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.LiveData.User.AuthenticationLiveData;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Repository.AuthenticationRepository;
import com.example.raceorganizer.Repository.RaceRepository;
import com.google.firebase.auth.FirebaseUser;

public class AddRaceViewModel extends ViewModel {


    private static AddRaceViewModel instance;
    private final MutableLiveData<Race> raceToAdd;
    private AuthenticationRepository userRepository;
    private RaceRepository raceRepository;
//    private final Race localRaceItem;

    private AddRaceViewModel(Application application) {
        raceToAdd = new MutableLiveData<>();
        userRepository = AuthenticationRepository.getInstance(application);
        raceRepository = RaceRepository.getInstance();
//        localRaceItem = new Race("");
    }


    public static synchronized AddRaceViewModel getInstance(Application application) {
        if (instance == null)
            instance = new AddRaceViewModel(application);
        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }


    public void addRace(Race race) {

        raceRepository.addRace(race);
    }

}