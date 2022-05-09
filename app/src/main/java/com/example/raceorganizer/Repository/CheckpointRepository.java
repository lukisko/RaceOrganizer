package com.example.raceorganizer.Repository;

import com.example.raceorganizer.Data.Dao.CheckpointDao;
import com.example.raceorganizer.Data.LiveData.Checkpoint.CheckpointsLiveData;

public class CheckpointRepository {
    private static CheckpointRepository instance;
    private CheckpointDao repository;

    private CheckpointRepository(){
        repository = CheckpointDao.getInstance();
    }

    public static synchronized CheckpointRepository getInstance() {
        if(instance == null)
            instance = new CheckpointRepository();
        return instance;
    }

    public void init(String id, String ownerRace) {
        repository.init(id,ownerRace);
    }



    public CheckpointsLiveData getCheckpoints() {
        return repository.getCheckpoints();
    }
}
