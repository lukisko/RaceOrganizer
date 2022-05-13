package com.example.raceorganizer.Data.LiveData.User;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.LiveData.Race.RaceLiveData;
import com.example.raceorganizer.Data.Model.RegisteredUser;
import com.example.raceorganizer.Data.Model.User;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

public class UserIdLiveData extends LiveData<RegisteredUser> {
    private DocumentReference reference;
    private ListenerRegistration listenerRegistration;

    private final EventListener<DocumentSnapshot> valueEventListener = new EventListener<DocumentSnapshot>() {
        RegisteredUser user;
        @Override
        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
            user = new RegisteredUser(
                   value.getId(),
                   value.getString("FirstName"),
                   value.getString("LastName")
            );
            setValue(user);
        }
    };

    public UserIdLiveData(DocumentReference reference){
        this.reference = reference;
    }
    @Override
    protected void onActive() {
        super.onActive();
        listenerRegistration = reference.addSnapshotListener(valueEventListener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        listenerRegistration.remove();
    }
}
