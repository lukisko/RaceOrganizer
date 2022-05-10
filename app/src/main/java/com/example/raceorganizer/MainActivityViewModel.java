package com.example.raceorganizer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.raceorganizer.Data.LiveData.User.LoggedInUserLiveData;
import com.example.raceorganizer.Repository.AuthenticationRepository;


public class MainActivityViewModel extends AndroidViewModel {
    private LoggedInUserLiveData userLiveData;
    private AuthenticationRepository userRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        userLiveData = new LoggedInUserLiveData();
        userRepository = AuthenticationRepository.getInstance(application);
    }

    public LoggedInUserLiveData getUserLiveData() {
        return userLiveData;
    }
    public void signOut()
    {
        userRepository.signOut();
    }

}
