package com.example.raceorganizer.Data.LiveData.Checkpoint;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Race;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

public class CheckpointLiveData extends LiveData<Checkpoint> {
    DocumentReference reference;
    private ListenerRegistration listenerRegistration;


    private final EventListener<DocumentSnapshot> valueEventListener = new EventListener<DocumentSnapshot>() {
        @Override
        public void onEvent(@Nullable DocumentSnapshot document, @Nullable FirebaseFirestoreException error) {
            Checkpoint checkpoint = new Checkpoint(
                    document.getId(),
                    document.getString("Name"),
                    Integer.parseInt(document.get("TotalPoints").toString()),
                    Integer.parseInt(document.get("PointsRecieved").toString()));
            setValue(checkpoint);
        }

    };


    public CheckpointLiveData(DocumentReference ref){
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
