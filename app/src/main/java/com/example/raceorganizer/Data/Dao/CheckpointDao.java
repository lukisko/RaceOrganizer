package com.example.raceorganizer.Data.Dao;

import com.example.raceorganizer.Data.LiveData.Checkpoint.CheckpointLiveData;
import com.example.raceorganizer.Data.LiveData.Checkpoint.CheckpointsLiveData;
import com.example.raceorganizer.Data.LiveData.Race.RaceLiveData;
import com.example.raceorganizer.Data.Model.Checkpoint;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

public class CheckpointDao {
    private static CheckpointDao instance;
    private CheckpointsLiveData checkpointsLiveData;
    private CheckpointLiveData currentCheckpointLiveData;
    private FirebaseFirestore databaseRef;


    private CheckpointDao(){
        databaseRef = FirebaseFirestore.getInstance();
    }

    public static synchronized CheckpointDao getInstance(){
        if(instance == null){
            return new CheckpointDao();
        }
        return instance;
    }


    public CheckpointsLiveData getCheckpoints(String raceId){
        Query allCheckpointsRef =  databaseRef.collection("Checkpoints").whereEqualTo("Race",raceId);
        checkpointsLiveData = new CheckpointsLiveData(allCheckpointsRef);
        return checkpointsLiveData;
    }

    public void addCheckpoint(Checkpoint checkpoint){
        Map<String, Object> data = new HashMap<>();
        data.put("Name", checkpoint.getName());
        data.put("PointsRecieved",checkpoint.getPointsReceived());
        data.put("TotalPoints", checkpoint.getTotalPoints());
        data.put("Race", checkpoint.getRaceId());
        databaseRef.collection("Races").add(data);
    }
    public void deleteCheckpoint(String id){
        databaseRef.collection("Checkpoints").document(id).delete();
    }

}
