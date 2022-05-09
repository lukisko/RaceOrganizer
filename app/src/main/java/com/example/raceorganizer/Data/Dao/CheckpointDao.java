package com.example.raceorganizer.Data.Dao;

import com.example.raceorganizer.Data.LiveData.Checkpoint.CheckpointLiveData;
import com.example.raceorganizer.Data.LiveData.Checkpoint.CheckpointsLiveData;
import com.example.raceorganizer.Data.LiveData.Race.RaceLiveData;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class CheckpointDao {
    private static CheckpointDao instance;
    private CheckpointsLiveData checkpointsLiveData;
    private CheckpointLiveData currentCheckpointLiveData;


    RaceLiveData asd;

    private CheckpointDao(){
    }

    public static synchronized CheckpointDao getInstance(){
        if(instance == null){
            return new CheckpointDao();
        }
        return instance;
    }

    public void init(String id , String ownerRace){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        if(!id.equals("")) {
            DocumentReference currentCheckpointRef = database.collection("Checkpoints").document(id);
            currentCheckpointLiveData = new CheckpointLiveData(currentCheckpointRef);
        }
        else if(!ownerRace.equals("")){
            Query allCheckpointsRef =  database.collection("Checkpoints").whereEqualTo("Race",ownerRace);
            checkpointsLiveData = new CheckpointsLiveData(allCheckpointsRef);
        }
    }

    public CheckpointsLiveData getCheckpoints(){
        return checkpointsLiveData;
    }

}
