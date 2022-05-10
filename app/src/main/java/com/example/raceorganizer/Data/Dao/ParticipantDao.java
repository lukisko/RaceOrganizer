package com.example.raceorganizer.Data.Dao;

import com.example.raceorganizer.Data.LiveData.Participant.ParticipantLiveData;
import com.example.raceorganizer.Data.LiveData.Participant.ParticipantsLiveData;
import com.example.raceorganizer.Data.Model.Participant;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

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
        Query allParticipantsRef = databaseRef.collection("Participants").whereEqualTo("Race", raceId);
        ParticipantsLiveData allParticipants = new ParticipantsLiveData(allParticipantsRef);
        return allParticipants;

    }

    public ParticipantLiveData getParticipant(String id) {
        DocumentReference currentParticipantRef = databaseRef.collection("Participants").document(id);
        ParticipantLiveData currentParticipant = new ParticipantLiveData(currentParticipantRef);
        return currentParticipant;
    }

    public void addParticipant(Participant participant) {
        Map<String, Object> data = new HashMap<>();
        data.put("Age", participant.getAge());
        data.put("FirstName", participant.getFirstName());
        data.put("LastName", participant.getLastName());
        data.put("Number", participant.getNumber());
        data.put("Points", participant.getPoints());
        data.put("TotalTime", participant.getTotalTime());

        databaseRef.collection("Participants").add(data);
    }

    public void addCheckpoint(String participantId, String checkpointId, String points) {
        Map<String, Object> data = new HashMap<>();
        data.put("PointsRecieved", points);

        databaseRef.collection("Participants")
                .document(participantId)
                .collection("Checkpoints")
                .document(checkpointId).set(data);
    }
}
