package com.example.raceorganizer.Data.LiveData.User;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.Model.Participant;
import com.example.raceorganizer.Data.Model.RegisteredUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class UserNameLiveData extends LiveData<RegisteredUser> {
    Query reference;
    private ListenerRegistration listenerRegistration;


    private final EventListener<QuerySnapshot> valueEventListener = new EventListener<QuerySnapshot>() {
        RegisteredUser user;
        @Override
        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
            if(value.getDocuments().size() > 0) {
                DocumentSnapshot document = value.getDocuments().get(0);
                user = new RegisteredUser(
                        document.getId(),
                        document.getString("FirstName"),
                        document.getString("LastName")
                );
            }
            setValue(user);
        }
    };




    public UserNameLiveData(Query ref) {
        reference = ref;

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
