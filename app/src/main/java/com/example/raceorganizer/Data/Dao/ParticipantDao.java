package com.example.raceorganizer.Data.Dao;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.example.raceorganizer.Data.LiveData.Participant.ParticipantCheckpointLiveData;
import com.example.raceorganizer.Data.LiveData.Participant.ParticipantLiveData;
import com.example.raceorganizer.Data.LiveData.Participant.ParticipantsLiveData;
import com.example.raceorganizer.Data.Model.Participant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ParticipantDao {
    private static ParticipantDao instance;
    private FirebaseFirestore databaseRef;

    private ParticipantDao() {
        databaseRef = FirebaseFirestore.getInstance();
    }

    public static synchronized ParticipantDao getInstance() {
        if (instance == null) {
            return new ParticipantDao();
        }
        return instance;
    }

    public ParticipantsLiveData getParticipants(String raceId) {
        Query allParticipantsRef = databaseRef.collection("Participants").whereArrayContains("Races", raceId);
        ParticipantsLiveData allParticipants = new ParticipantsLiveData(allParticipantsRef);
        return allParticipants;

    }

    public ParticipantLiveData getParticipant(String id) {
        DocumentReference currentParticipantRef = databaseRef.collection("Participants").document(id);
        ParticipantLiveData currentParticipant = new ParticipantLiveData(currentParticipantRef);
        return currentParticipant;
    }

    public Task<DocumentReference> addParticipant(Participant participant) {
        final ParticipantLiveData liveData = new ParticipantLiveData();
        Map<String, Object> data = new HashMap<>();
        data.put("Age", participant.getAge());
        data.put("FirstName", participant.getFirstName());
        data.put("LastName", participant.getLastName());
        data.put("Number", participant.getNumber());
        data.put("Points", participant.getPoints());
        data.put("TotalTime", participant.getTotalTime());
        data.put("Races", participant.getRaceIds());

        return databaseRef.collection("Participants").add(data);

    }

    public void addCheckpoint(String participantId, String checkpointId, String points) {
        Map<String, Object> data = new HashMap<>();
        data.put("PointsRecieved", points);

        databaseRef.collection("Participants").document(participantId).collection("Checkpoints").add(data);
    }
    public void addPoints(String id, int points){
        System.out.println("HERE "+id + " " +points);
        Map<String, Object> data = new HashMap<>();
        data.put("Points", points);
        databaseRef.collection("Participants").document(id).update(data);
    }
    public void addRace(String participantId, String raceId){

        databaseRef.collection("Participants")
                .document(participantId).update("Races", FieldValue.arrayUnion(raceId));
    }
    public ParticipantCheckpointLiveData getParticipantCheckpoints(String participantId){
        CollectionReference ref = databaseRef.collection("Participants").document(participantId).collection("Checkpoints");
        ParticipantCheckpointLiveData liveData = new ParticipantCheckpointLiveData(ref);
        return liveData;
    };



}
