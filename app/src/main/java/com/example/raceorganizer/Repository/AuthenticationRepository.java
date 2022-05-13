package com.example.raceorganizer.Repository;

import android.app.Application;

import com.example.raceorganizer.Data.Dao.AuthenticationDao;
import com.example.raceorganizer.Data.LiveData.User.AuthenticationLiveData;
import com.example.raceorganizer.Data.Model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class AuthenticationRepository {
    private final AuthenticationLiveData currentUser;
    private final Application application;
    private static AuthenticationRepository instance;

    private AuthenticationDao authenticationDao;

    private AuthenticationRepository(Application _application) {
        this.application = _application;
        currentUser = new AuthenticationLiveData();
        authenticationDao = AuthenticationDao.getInstance();
    }

    public static synchronized AuthenticationRepository getInstance(Application app) {
        if (instance == null)
            instance = new AuthenticationRepository(app);
        return instance;
    }

    public AuthenticationLiveData getCurrentUser() {
        return currentUser;
    }

    public void signOut() {
        authenticationDao.signOut();

    }

    public Task<AuthResult> signUp(User user) {
       return authenticationDao.signUp(user);
    }

    public Task<AuthResult> signIn(User user) {

        return authenticationDao.signIn(user);
    }

    public Task<Void> createFireBaseUser(User user) {
        return authenticationDao.createUserInFirebase(user);
    }


}
