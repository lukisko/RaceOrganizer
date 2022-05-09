package com.example.raceorganizer.ui.login_activity;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.LoggedInUserPersistence.LoggedInUserRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {

    private final LoggedInUserRepository userRepository;

    public LoginViewModel(Application app) {
        super(app);
        userRepository = LoggedInUserRepository.getInstance(app);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }


}
