package com.example.raceorganizer.Ui.Profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.LiveData.Moderator.ModeratorLiveData;
import com.example.raceorganizer.Repository.LoggedInUserRepository;
import com.example.raceorganizer.Repository.ModeratorRepositori;
import com.google.firebase.auth.FirebaseUser;

public class ProfileViewModel extends AndroidViewModel {

    private LoggedInUserRepository userRepository;
    private ModeratorRepositori moderatorRepositori;

    public ProfileViewModel(@NonNull Application app){
        super(app);
       userRepository = LoggedInUserRepository.getInstance(app);
       moderatorRepositori = ModeratorRepositori.getInstance();
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    public ModeratorLiveData getModerator(String id){
        return moderatorRepositori.getModerator(id);
    }
}
