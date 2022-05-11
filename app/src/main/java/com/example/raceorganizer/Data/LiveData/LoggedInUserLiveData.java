package com.example.raceorganizer.Data.LiveData;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoggedInUserLiveData extends LiveData<FirebaseUser> {
    private final FirebaseAuth.AuthStateListener listener=firebaseAuth -> setValue(firebaseAuth.getCurrentUser());

    @Override
    protected void onActive() {
        super.onActive();
        FirebaseAuth.getInstance().addAuthStateListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        FirebaseAuth.getInstance().addAuthStateListener(listener);
    }
}
