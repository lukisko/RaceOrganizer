package com.example.raceorganizer.Data.LiveData.Checkpoint;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.Dao.CheckpointDao;
import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.RegisteredUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.okhttp.internal.DiskLruCache;

import java.util.ArrayList;
import java.util.Date;

public class CheckpointsLiveData extends LiveData<ArrayList<Checkpoint>> {

    Query reference;
    private ListenerRegistration listenerRegistration;


    private final EventListener<QuerySnapshot> valueEventListener = new EventListener<QuerySnapshot>() {

        @Override
        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
            ArrayList<Checkpoint> checkpoints = new ArrayList<>();
            for (DocumentSnapshot document:value.getDocuments()) {
                Checkpoint checkpoint = new Checkpoint();
                checkpoint.setId(document.getId());
                checkpoint.setName(document.getString("Name"));
                checkpoint.setTotalPoints(Integer.parseInt(document.get("TotalPoints").toString()));
                //checkpoint.setPointsReceived(Integer.parseInt(document.get("PointsRecieved").toString()));
                checkpoint.setRaceId(document.getString("Race"));
                checkpoint.setModerators((ArrayList<String>) document.get("Moderators"));
                checkpoints.add(checkpoint);
            }
            setValue(checkpoints);
        }
    };






    public CheckpointsLiveData(Query ref) {
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
