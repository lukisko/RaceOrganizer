package com.example.raceorganizer.Data.LiveData.Moderator;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.RegisteredUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

public class ModeratorLiveData extends LiveData<RegisteredUser> {
    DocumentReference reference;
    private ListenerRegistration listenerRegistration;


    private final EventListener<DocumentSnapshot> valueEventListener = new EventListener<DocumentSnapshot>() {
        @Override
        public void onEvent(@Nullable DocumentSnapshot document, @Nullable FirebaseFirestoreException error) {
            RegisteredUser moderator = new RegisteredUser(
                    document.getId(),
                    document.getString("FirstName"),
                    document.getString("LastName")
            );
            setValue(moderator);
        }

    };


    public ModeratorLiveData(DocumentReference ref){
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
