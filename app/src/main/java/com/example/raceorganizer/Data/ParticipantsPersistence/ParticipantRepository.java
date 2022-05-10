package com.example.raceorganizer.Data.ParticipantsPersistence;

import com.google.firebase.database.DatabaseReference;

public class ParticipantRepository {
    private static ParticipantRepository instance;
    private DatabaseReference reference;
    private ParticipantLiveData participants;

    private ParticipantRepository() {
    }

    public static ParticipantRepository getInstance() {
        if (instance == null) {
            instance = new ParticipantRepository();
        }
        return instance;
    }
    
}
