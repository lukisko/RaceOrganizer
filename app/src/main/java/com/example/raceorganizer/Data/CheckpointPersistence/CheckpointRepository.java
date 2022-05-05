package com.example.raceorganizer.Data.CheckpointPersistence;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
