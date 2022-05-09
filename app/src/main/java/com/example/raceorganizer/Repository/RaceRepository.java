package com.example.raceorganizer.Repository;

import com.example.raceorganizer.Data.Dao.RaceDao;
import com.example.raceorganizer.Data.LiveData.Race.RaceLiveData;
import com.example.raceorganizer.Data.LiveData.Race.RacesLiveData;

public class RaceRepository {
    private RaceDao repository;
    private static RaceRepository instance;

    private RaceRepository() {
        repository = RaceDao.getInstance();
    }

    public void init(String id, String user) {
        repository.init(id,user);
    }

    public static synchronized RaceRepository getInstance() {
        if (instance == null)
            instance = new RaceRepository();
        return instance;
    }

    public RacesLiveData getAllRaces(){
        return repository.getRaces();
    }
    public RaceLiveData getRace(){
        return repository.getRace("1");
    }

}
