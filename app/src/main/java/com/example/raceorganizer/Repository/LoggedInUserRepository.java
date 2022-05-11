package com.example.raceorganizer.Repository;

import android.app.Application;

import com.example.raceorganizer.Data.LiveData.LoggedInUserLiveData;
import com.firebase.ui.auth.AuthUI;

public class LoggedInUserRepository {
    private final LoggedInUserLiveData currentUser;
    private final Application application;
    private static LoggedInUserRepository instance;

    private LoggedInUserRepository(Application _application) {
        this.application = _application;
        currentUser = new LoggedInUserLiveData();
    }

    public static synchronized LoggedInUserRepository getInstance(Application app) {
        if (instance == null)
            instance = new LoggedInUserRepository(app);
        return instance;
    }

    public LoggedInUserLiveData getCurrentUser() {
        return currentUser;
    }

    public void signOut() {
        AuthUI.getInstance().signOut(application.getApplicationContext());
    }
}
