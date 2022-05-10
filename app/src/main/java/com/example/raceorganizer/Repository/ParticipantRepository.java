package com.example.raceorganizer.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Participant;
import com.example.raceorganizer.Data.Model.Race;

import java.util.ArrayList;
import java.util.Objects;

public class ParticipantRepository {
    private static ParticipantRepository instance;
    private static final Object lock = new Object();

    private MutableLiveData<ArrayList<Participant>> participants;

    private ParticipantRepository(){
        ArrayList<Participant> particip  = new ArrayList<Participant>();
        participants = new MutableLiveData<>(particip);
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

    public LiveData<ArrayList<Participant>> getAllParticipants(Race race) {
        return participants;
    }

    public void addParticipant(Race race, Participant participant){
        //nothing for now.
    }
}
