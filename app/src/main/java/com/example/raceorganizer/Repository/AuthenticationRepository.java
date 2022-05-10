package com.example.raceorganizer.Repository;

import android.app.Application;

import com.example.raceorganizer.Data.LiveData.User.LoggedInUserLiveData;
import com.example.raceorganizer.Data.Model.User;
import com.firebase.ui.auth.AuthUI;

public class AuthenticationRepository {
    private final LoggedInUserLiveData currentUser;
    private final Application application;
    private static AuthenticationRepository instance;

    private AuthenticationRepository(Application _application) {
        this.application = _application;
        currentUser = new LoggedInUserLiveData();
    }

    public static synchronized AuthenticationRepository getInstance(Application app) {
        if (instance == null)
            instance = new AuthenticationRepository(app);
        return instance;
    }

    public LoggedInUserLiveData getCurrentUser() {
        return currentUser;
    }

    public void signOut() {
        AuthUI.getInstance().signOut(application.getApplicationContext());
    }

    public void signUp(User user) {
        //To be implemented returns a Task and call repositoryDAO
    }

    public void signIn(User user) {//To be implemented returns a Task and call repositoryDAO
    }

}
