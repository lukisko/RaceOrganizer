package com.example.raceorganizer.Ui.login_activity;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;
import com.example.raceorganizer.Repository.LoggedInUserRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {

    private final LoggedInUserRepository userRepository;

    public LoginViewModel(@NonNull Application app) {
        super(app);
        userRepository = LoggedInUserRepository.getInstance(app);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }


}
