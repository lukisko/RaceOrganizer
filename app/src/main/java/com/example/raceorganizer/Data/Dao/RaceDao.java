package com.example.raceorganizer.Data.Dao;

import com.example.raceorganizer.Data.LiveData.Race.RaceLiveData;
import com.example.raceorganizer.Data.LiveData.Race.RacesLiveData;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class RaceDao {
    private static RaceDao instance;
    private RacesLiveData racesLiveData;
    private RaceLiveData currentRaceLiveData;

    private RaceDao(){
    }

    public static synchronized RaceDao getInstance(){
        if(instance == null){
            return new RaceDao();
        }
        return instance;
    }

    public void init(String id, String user){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        if(!id.equals("")) {
            DocumentReference specificRaceRef = database.collection("Races").document(id);
            currentRaceLiveData = new RaceLiveData(specificRaceRef);
        }
        else if(!user.equals("")){
            Query allRacesRef = database.collection("Races").whereEqualTo("RaceOwner", user);
            racesLiveData = new RacesLiveData(allRacesRef);
        }
    }

    public RacesLiveData getRaces(){
        return racesLiveData;
    }

    public RaceLiveData getRace(String name){
        return currentRaceLiveData;
    }
}
