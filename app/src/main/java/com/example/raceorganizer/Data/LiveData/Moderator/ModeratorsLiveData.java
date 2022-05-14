package com.example.raceorganizer.Data.LiveData.Moderator;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.Model.RegisteredUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ModeratorsLiveData extends LiveData<ArrayList<RegisteredUser>> {
    Query reference;
    private ListenerRegistration listenerRegistration;

    private final EventListener<QuerySnapshot> valueEventListener = new EventListener<QuerySnapshot>() {
        ArrayList<RegisteredUser> moderators = new ArrayList<>();
        @Override
        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
            for (DocumentSnapshot document :value.getDocuments()) {
                RegisteredUser moderator = new RegisteredUser(
                        document.getId(),
                        document.getString("FirstName"),
                        document.getString("LastName")
                );
                moderators.add(moderator);
            }
            setValue(moderators);
        }

    };


    public ModeratorsLiveData(Query ref){
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
