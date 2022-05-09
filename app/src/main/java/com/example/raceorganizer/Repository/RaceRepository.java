package com.example.raceorganizer.Repository;

import com.example.raceorganizer.Data.Dao.RaceDao;
import com.example.raceorganizer.Data.LiveData.CheckpointLiveData;
import com.example.raceorganizer.Data.LiveData.RaceLiveData;
import com.example.raceorganizer.Data.LiveData.RacesLiveData;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RaceRepository {
    private static RaceRepository instance;
    private RaceDao racePersistence;


    private RaceRepository(){}

    public static synchronized RaceRepository getInstance() {
        if(instance == null)
            instance = new RaceRepository();
        return instance;
    }

    public void init(String id) {
        racePersistence.init(id);
    }



    public RacesLiveData getAllRaces() {
        return racePersistence.getRaces();
    }
    public RaceLiveData getRace(){
        return racePersistence.getRace();
    }
}
