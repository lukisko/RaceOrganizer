package com.example.raceorganizer.Repository;

import com.example.raceorganizer.Data.LiveData.CheckpointLiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CheckpointRepository {
    private static CheckpointRepository instance;
    private DatabaseReference myRef;
    private CheckpointLiveData checkpoints;

    private CheckpointRepository(){}

    public static synchronized CheckpointRepository getInstance() {
        if(instance == null)
            instance = new CheckpointRepository();
        return instance;
    }

    public void init() {
        CollectionReference db = FirebaseFirestore.getInstance().collection("Checkpoints");
        checkpoints = new CheckpointLiveData(db);
        checkpoints.startListener();
    }



    public CheckpointLiveData getCheckpoints() {
        return checkpoints;
    }
}
