package com.example.raceorganizer.Data.LiveData.Participant;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Participant;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ParticipantCheckpointLiveData extends LiveData<ArrayList<Checkpoint>> {
    CollectionReference reference;
    private ListenerRegistration listenerRegistration;


    private final EventListener<QuerySnapshot> valueEventListener = new EventListener<QuerySnapshot>() {
        ArrayList<Checkpoint> checkpoints = new ArrayList<>();
        @Override
        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
            for (DocumentSnapshot document:value.getDocuments()) {
                Checkpoint checkpoint = new Checkpoint();
                checkpoint.setId(document.getId());
                checkpoint.setPointsReceived(Integer.parseInt(document.getString("PointsRecieved")));
                checkpoints.add(checkpoint);
            }
            setValue(checkpoints);
        }
    };




    public ParticipantCheckpointLiveData(CollectionReference ref) {
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
