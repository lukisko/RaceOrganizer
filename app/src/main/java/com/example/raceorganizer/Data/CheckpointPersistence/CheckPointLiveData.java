package com.example.raceorganizer.Data.CheckpointPersistence;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CheckPointLiveData extends LiveData<ArrayList<Checkpoint>> {
    CollectionReference databaseReference;

    public CheckPointLiveData(CollectionReference ref) {
        databaseReference = ref;
    }

    @Override
    protected void onActive() {
        super.onActive();

    }

    public void startListener(){
        databaseReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                ArrayList<Checkpoint> checkpoints = new ArrayList<>();
                if (value.size() > 0){
                    for (DocumentSnapshot snapshot:value.getDocuments()) {
//                        checkpoints.add(new Checkpoint(snapshot.get("Name").toString(),
//                                Integer.parseInt(snapshot.get("PointsRecieved").toString()),
//                                Integer.parseInt(snapshot.get("TotalPoints").toString())));
                    }
                }
                setValue(checkpoints);
            }
        });
    }

}