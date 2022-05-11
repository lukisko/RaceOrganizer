package com.example.raceorganizer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.raceorganizer.Data.LiveData.User.AuthenticationLiveData;
import com.example.raceorganizer.Repository.AuthenticationRepository;


public class MainActivityViewModel extends AndroidViewModel {
    private AuthenticationLiveData userLiveData;
    private AuthenticationRepository userRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        userLiveData = new AuthenticationLiveData();
        userRepository = AuthenticationRepository.getInstance(application);
    }

    public AuthenticationLiveData getUserLiveData() {
        return userLiveData;
    }
    public void signOut()
    {
        userRepository.signOut();
    }

}
