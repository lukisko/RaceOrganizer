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
    private RacesLiveData liveData;
    private FirebaseFirestore database;
    private CollectionReference allRacesRef;
    private DocumentReference raceRef;


    RaceLiveData asd;

    private RaceDao(){
    }

    public static synchronized RaceDao getInstance(){
        if(instance == null){
            return new RaceDao();
        }
        return instance;
    }

    public void init(){
        database = FirebaseFirestore.getInstance();
        allRacesRef = database.collection("Races");
        liveData = new RacesLiveData(allRacesRef);
        asd = new RaceLiveData(database.collection("Races").document("1"));
    }

    public RacesLiveData getRaces(){
        return liveData;
    }

    public RaceLiveData getRace(String name){
        return asd;
    }
}
