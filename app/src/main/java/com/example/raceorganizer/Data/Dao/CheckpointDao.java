package com.example.raceorganizer.Data.Dao;

import android.util.Log;

import com.example.raceorganizer.Data.LiveData.Checkpoint.CheckpointLiveData;
import com.example.raceorganizer.Data.LiveData.Checkpoint.CheckpointsLiveData;
import com.example.raceorganizer.Data.LiveData.Moderator.ModeratorLiveData;
import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Repository.CheckpointRepository;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CheckpointDao {
    private static CheckpointDao instance;
    private FirebaseFirestore databaseRef;


    private CheckpointDao() {
        databaseRef = FirebaseFirestore.getInstance();
    }

    public static synchronized CheckpointDao getInstance() {
        if (instance == null) {
            return new CheckpointDao();
        }
        return instance;
    }


    public CheckpointLiveData getCheckpoint(String checkpointId) {
        DocumentReference currentCheckpointRef = databaseRef.collection("Checkpoints").document(checkpointId);
        CheckpointLiveData currentCheckpoint = new CheckpointLiveData(currentCheckpointRef);
        return currentCheckpoint;
    }

    public CheckpointsLiveData getCheckpoints(String raceId) {
        Query allCheckpointsRef = databaseRef.collection("Checkpoints").whereEqualTo("Race", raceId);
        CheckpointsLiveData checkpointsLiveData = new CheckpointsLiveData(allCheckpointsRef);
        return checkpointsLiveData;
    }

    public void addCheckpoint(Checkpoint checkpoint) {
        Map<String, Object> data = new HashMap<>();
        data.put("Name", checkpoint.getName());
        data.put("TotalPoints", checkpoint.getTotalPoints());
        data.put("Race", checkpoint.getRaceId());
        data.put("Moderators", checkpoint.getModerators());
        databaseRef.collection("Checkpoints").add(data);
    }

    public void deleteCheckpoint(String id) {
        databaseRef.collection("Checkpoints").document(id).delete();
    }

    public void addModerator(String checkpointId, String id) {
        databaseRef.collection("Checkpoints").document(checkpointId).update("Moderators", FieldValue.arrayUnion(id));
    }


}
