package com.example.raceorganizer.Data.ParticipantsPersistence;

import com.example.raceorganizer.Data.CheckpointPersistence.CheckPointLiveData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ParticipantsDAO {
    private ParticipantsDAO instance;
    private DatabaseReference dbReference;
    private ParticipantLiveData participantLiveData;
    private CheckPointLiveData checkpointLiveData;


    private ParticipantsDAO() {
    }

    public ParticipantsDAO getInstance() {
        if(instance==null)
        {
         instance=new ParticipantsDAO();
        }
        return instance;
    }

    public void init(String raceId) {
        CollectionReference db = FirebaseFirestore.getInstance().collection("Participants");
        //participantLiveData = new ParticipantLiveData(db);
        checkpointLiveData = new CheckPointLiveData(db);
        //checkpoints.startListener();
    }
}
