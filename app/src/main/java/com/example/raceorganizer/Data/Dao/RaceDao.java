package com.example.raceorganizer.Data.Dao;

import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.LiveData.Race.RaceLiveData;
import com.example.raceorganizer.Data.LiveData.Race.RacesLiveData;
import com.example.raceorganizer.Data.Model.Race;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RaceDao {
    private static RaceDao instance;
    private FirebaseFirestore databaseRef;

    private RaceDao(){
        databaseRef = FirebaseFirestore.getInstance();
    }

    public static synchronized RaceDao getInstance(){
        if(instance == null){
            return new RaceDao();
        }
        return instance;
    }


    public RacesLiveData getRaces(String user){
        Query allRacesRef = databaseRef.collection("Races").whereEqualTo("RaceOwner", user);
        RacesLiveData racesLiveData = new RacesLiveData(allRacesRef);
        return racesLiveData;
    }

    public RaceLiveData getRace(String id){
        DocumentReference specificRaceRef = databaseRef.collection("Races").document(id);
        RaceLiveData currentRaceLiveData = new RaceLiveData(specificRaceRef);
        return currentRaceLiveData;
    }

    public void addRace(Race race){
        Map<String, Object> data = new HashMap<>();
        data.put("Name", race.getName());
        data.put("RaceOwner", race.getRaceOwner());
        data.put("Start", race.getStart());
        data.put("End", race.getEnd());
        data.put("Type", race.getRaceType());

        databaseRef.collection("Races").document(race.getId()).set(data);
    }

    public void deleteRace(String id){
        databaseRef.collection("Races").document(id).delete();
    }

    public LiveData<ArrayList<Race>> getRacesParticipant(String participantId) {

        return null;
    }

}
