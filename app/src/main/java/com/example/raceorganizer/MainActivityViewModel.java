package com.example.raceorganizer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.raceorganizer.Data.LoggedInUserPersistence.LoggedInUserLiveData;
import com.example.raceorganizer.Data.LoggedInUserPersistence.LoggedInUserRepository;


public class MainActivityViewModel extends AndroidViewModel {
    private LoggedInUserLiveData userLiveData;
    private LoggedInUserRepository userRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        userLiveData = new LoggedInUserLiveData();
        userRepository = LoggedInUserRepository.getInstance(application);
    }

    public LoggedInUserLiveData getUserLiveData() {
        return userLiveData;
    }
    public void signOut()
    {
        userRepository.signOut();
    }

}
