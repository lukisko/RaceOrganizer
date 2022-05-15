package com.example.raceorganizer.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.raceorganizer.Data.Dao.ParticipantDao;
import com.example.raceorganizer.Data.LiveData.Participant.ParticipantCheckpointLiveData;
import com.example.raceorganizer.Data.LiveData.Participant.ParticipantLiveData;
import com.example.raceorganizer.Data.LiveData.Participant.ParticipantsLiveData;
import com.example.raceorganizer.Data.Model.Participant;
import com.example.raceorganizer.Data.Model.Race;

import java.util.ArrayList;

public class ParticipantRepository {
    private static ParticipantRepository instance;
    private static final Object lock = new Object();

    private ParticipantDao repository;

    private ParticipantRepository(){
        repository = ParticipantDao.getInstance();
    }

    public static ParticipantRepository getInstance() {
        if (instance != null){
            return instance;
        }
        synchronized (lock){
            instance = new ParticipantRepository();
        }
        return instance;
    }

    public ParticipantsLiveData getAllParticipants(String raceId) {
        return repository.getParticipants(raceId);
    }

    public ParticipantLiveData getParticipant(String id) {
        return repository.getParticipant(id);
    }

    public ParticipantLiveData addParticipant(Participant participant){
        return repository.addParticipant(participant);
    }

    public void addCheckpoint(String participantId, String checkpointId, String points) {
        repository.addCheckpoint(participantId,checkpointId,points);
    }

    public ParticipantCheckpointLiveData getParticipantCheckpoints(String participantId){
        return repository.getParticipantCheckpoints(participantId);
    }
}
