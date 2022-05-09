package com.example.raceorganizer.Data.Dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.raceorganizer.Data.LiveData.RaceLiveData;
import com.example.raceorganizer.Data.LiveData.RacesLiveData;
import com.example.raceorganizer.Data.Model.Race;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RaceDao {
    private static RaceDao instance;
    private RacesLiveData racesLiveData;
    private RaceLiveData raceLiveData;
    private FirebaseFirestore database;
    private CollectionReference allRacesRef;
    private DocumentReference specificRaceRef;



    RaceLiveData asd;

    private RaceDao(){
    }

    public static synchronized RaceDao getInstance(){
        if(instance == null){
            return new RaceDao();
        }
        return instance;
    }

    public void init(String id){
        database = FirebaseFirestore.getInstance();
        if(id == null){
            allRacesRef = database.collection("Races");
            racesLiveData = new RacesLiveData(allRacesRef);
        }
        else{
            specificRaceRef = database.collection("Race").document(id);
            raceLiveData = new RaceLiveData(specificRaceRef);
        }
    }

    public RacesLiveData getRaces(){
        return racesLiveData;
    }

    public RaceLiveData getRace(){
        return raceLiveData;
    }
}
