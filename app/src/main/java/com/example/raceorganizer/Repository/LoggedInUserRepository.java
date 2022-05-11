package com.example.raceorganizer.Repository;

import android.app.Application;

import com.example.raceorganizer.Data.Dao.UserDao;
import com.example.raceorganizer.Data.LiveData.User.LoggedInUserLiveData;
import com.example.raceorganizer.Data.LiveData.User.UserIdLiveData;
import com.example.raceorganizer.Data.LiveData.User.UserNameLiveData;
import com.firebase.ui.auth.AuthUI;

public class LoggedInUserRepository {
    private final LoggedInUserLiveData currentUser;
    private final Application application;
    private static LoggedInUserRepository instance;
    private UserDao persistence;

    private LoggedInUserRepository(Application _application) {
        this.application = _application;
        currentUser = new LoggedInUserLiveData();
        persistence = UserDao.getInstance();
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

    public UserIdLiveData getUserById(String id){
        return persistence.getUserById(id);
    }

    public UserNameLiveData getUserByName(String firstName, String lastName){
        return persistence.getUserByName(firstName, lastName);
    }


}
