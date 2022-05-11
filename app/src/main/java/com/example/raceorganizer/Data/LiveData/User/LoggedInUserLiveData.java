package com.example.raceorganizer.Data.LiveData.User;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.ListenerRegistration;

public class LoggedInUserLiveData extends LiveData<FirebaseUser> {

    private ListenerRegistration listenerRegistration;
    private final FirebaseAuth.AuthStateListener listener = firebaseAuth -> setValue(firebaseAuth.getCurrentUser());

    public LoggedInUserLiveData(){

    }

    @Override
    protected void onActive() {
        super.onActive();
        FirebaseAuth.getInstance().addAuthStateListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        FirebaseAuth.getInstance().removeAuthStateListener(listener);
        FirebaseAuth.getInstance().removeAuthStateListener(listener);
        FirebaseAuth.getInstance().removeAuthStateListener(listener);
    }
}
