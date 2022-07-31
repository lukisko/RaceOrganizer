package com.example.raceorganizer.Data.Dao;

import android.app.Application;
import android.widget.Toast;

import com.example.raceorganizer.Data.LiveData.User.AuthenticationLiveData;
import com.example.raceorganizer.Data.Model.User;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationDao {
    private static AuthenticationDao instance;
    private final FirebaseAuth databaseRefAuthentication;
    private final FirebaseFirestore databaseRef;

    private AuthenticationDao() {
        databaseRefAuthentication = FirebaseAuth.getInstance();
        databaseRef = FirebaseFirestore.getInstance();
    }

    public static synchronized AuthenticationDao getInstance() {
        if (instance == null) {
            return new AuthenticationDao();
        }
        return instance;
    }

    public Task<AuthResult> signIn(User user) {
        return databaseRefAuthentication.signInWithEmailAndPassword(user.getUsername(), user.getPassword());
    }

    public void signOut() {
        databaseRefAuthentication.signOut();
    }

    public Task<AuthResult> signUp(User user) {
        return databaseRefAuthentication.createUserWithEmailAndPassword(user.getUsername(), user.getPassword());

    }

    public Task<Void> createUserInFirebase(User user) {
        Map<String, String> newUser = new HashMap<String, String>();
        newUser.put("FirstName", user.getFirstName());
        newUser.put("LastName", user.getLastName());
        return databaseRef.collection("LoggedInUser").document(user.getId()).set(newUser);
    }

}
