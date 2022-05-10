package com.example.raceorganizer.Repository;

import com.example.raceorganizer.Data.Dao.CheckpointDao;
import com.example.raceorganizer.Data.LiveData.Checkpoint.CheckpointsLiveData;
import com.example.raceorganizer.Data.Model.Checkpoint;

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




    public CheckpointsLiveData getCheckpoints(String raceId) {
        return repository.getCheckpoints(raceId);
    }

    public void addCheckpoint(Checkpoint checkpoint){
        repository.addCheckpoint(checkpoint);
    }
    public void deleteCheckpoint(String id){
        repository.deleteCheckpoint(id);
    }
}
