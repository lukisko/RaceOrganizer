package com.example.raceorganizer.Data.LiveData.Checkpoint;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.Model.Checkpoint;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

public class CheckpointLiveData extends LiveData<Checkpoint> {
    DocumentReference reference;
    private ListenerRegistration listenerRegistration;

    public CheckpointLiveData(DocumentReference ref) {
        reference = ref;
    }

    private final EventListener<DocumentSnapshot> valueEventListener = new EventListener<DocumentSnapshot>() {
        Checkpoint checkpoint = new Checkpoint();
        @Override
        public void onEvent(@Nullable DocumentSnapshot document, @Nullable FirebaseFirestoreException error) {

            Log.i("Id checkpoint DAO from db", document.getId());
            checkpoint.setId(document.getId());
            checkpoint.setName(document.getString("Name"));
            checkpoint.setTotalPoints(Integer.parseInt(document.get("TotalPoints").toString()));
            checkpoint.setRaceId(document.getString("Race"));
            setValue(checkpoint);
        }

    };
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
