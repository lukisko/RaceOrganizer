package com.example.raceorganizer.Data.RacePersistence;

import androidx.lifecycle.LiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class RaceRepository {

    private static RaceRepository instance;
    private DatabaseReference reference;
    private RaceLiveData race;

    private RaceRepository() {

    }

    public static synchronized RaceRepository getInstance() {
        if (instance == null) {
            instance = new RaceRepository();
        }
        return instance;
    }

    public void init(String userId) {
       // reference=

    }
}
