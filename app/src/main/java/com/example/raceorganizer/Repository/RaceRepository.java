package com.example.raceorganizer.Repository;

import com.example.raceorganizer.Data.Dao.RaceDao;
import com.example.raceorganizer.Data.LiveData.RaceLiveData;
import com.example.raceorganizer.Data.LiveData.RacesLiveData;

public class RaceRepository {
    private RaceDao raceDao;
    private static RaceRepository instance;

    private RaceRepository() {
        raceDao = RaceDao.getInstance();
    }

    public void init() {
        raceDao.init();
    }

    public static synchronized RaceRepository getInstance() {
        if (instance == null)
            instance = new RaceRepository();
        return instance;
    }

    public RacesLiveData getAllRaces(){
        return raceDao.getRaces();
    }
    public RaceLiveData getRace(){
        return raceDao.getRace("1");
    }

}
