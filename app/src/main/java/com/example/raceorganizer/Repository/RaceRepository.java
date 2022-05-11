package com.example.raceorganizer.Repository;

import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.Dao.RaceDao;
import com.example.raceorganizer.Data.LiveData.Race.RaceLiveData;
import com.example.raceorganizer.Data.LiveData.Race.RacesLiveData;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Data.Model.RaceType;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class RaceRepository {
    private RaceDao repository;
    private static RaceRepository instance;

    private RaceRepository() {
        repository = RaceDao.getInstance();
    }


    public static synchronized RaceRepository getInstance() {
        if (instance == null)
            instance = new RaceRepository();
        return instance;
    }

    public RacesLiveData getAllRaces(String user){
        return repository.getRaces(user);
    }
    public RaceLiveData getRace(String id){
        return repository.getRace(id);
    }
    public void addRace(Race race){
        repository.addRace(race);
    }
    public void deleteRace(String id){
        repository.deleteRace(id);
    }
    public LiveData<ArrayList<Race>> getRacesParticipant(){
        return repository.getRacesParticipant("1");
    }

}
