package com.example.raceorganizer.Ui.login_activity;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.example.raceorganizer.Data.Model.User;
import com.example.raceorganizer.Repository.AuthenticationRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {

    private final AuthenticationRepository userRepository;

    public LoginViewModel(@NonNull Application app) {
        super(app);
        userRepository = AuthenticationRepository.getInstance(app);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    public Task<AuthResult> signUp(User userToSignUp) {
        return userRepository.signUp(userToSignUp);
    }

    public Task<AuthResult> signIn(User userToSignUp) {
        return userRepository.signIn(userToSignUp);
    }

    public Task<Void> createUserInDatabase(User userToSignUp) {
        return userRepository.createFireBaseUser(userToSignUp);
    }


}
