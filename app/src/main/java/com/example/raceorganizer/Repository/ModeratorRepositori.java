package com.example.raceorganizer.Repository;

import com.example.raceorganizer.Data.Dao.CheckpointDao;
import com.example.raceorganizer.Data.Dao.ModeratorDao;
import com.example.raceorganizer.Data.LiveData.Checkpoint.CheckpointLiveData;
import com.example.raceorganizer.Data.LiveData.Checkpoint.CheckpointsLiveData;
import com.example.raceorganizer.Data.LiveData.Moderator.ModeratorLiveData;
import com.example.raceorganizer.Data.LiveData.Moderator.ModeratorsLiveData;
import com.example.raceorganizer.Data.Model.Checkpoint;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class ModeratorRepositori {
    private static ModeratorRepositori instance;
    private ModeratorDao repository;

    private ModeratorRepositori(){
        repository = ModeratorDao.getInstance();
    }

    public static synchronized ModeratorRepositori getInstance() {
        if(instance == null)
            instance = new ModeratorRepositori();
        return instance;
    }


    public ModeratorsLiveData getModerators(ArrayList<String> moderatorsIds){
        return repository.getModerators(moderatorsIds);
    }

    public ModeratorLiveData getModerator(String id) {
        return repository.getModerator(id);
    }


}
