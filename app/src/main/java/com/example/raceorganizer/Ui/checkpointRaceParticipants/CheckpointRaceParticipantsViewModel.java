package com.example.raceorganizer.Ui.checkpointRaceParticipants;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.LiveData.Participant.ParticipantLiveData;
import com.example.raceorganizer.Data.LiveData.Participant.ParticipantsLiveData;
import com.example.raceorganizer.Data.LiveData.Race.RaceLiveData;
import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Participant;
import com.example.raceorganizer.Repository.ParticipantRepository;
import com.example.raceorganizer.Repository.RaceRepository;

import java.util.ArrayList;

public class CheckpointRaceParticipantsViewModel extends ViewModel {
    //TODO make logic for checkpoint list view model
    private MutableLiveData<ArrayList<Participant>> participants;
    private RaceRepository raceRepository;
    private ParticipantRepository participantRepository;


    public CheckpointRaceParticipantsViewModel() {
        participants = new MutableLiveData<>(new ArrayList<>());
        ArrayList<Participant> allRaceParticipants = participants.getValue();

        raceRepository = RaceRepository.getInstance();
        participantRepository = ParticipantRepository.getInstance();
    }

    public LiveData<ArrayList<Participant>> getParticipants(String raceId) {
        return participantRepository.getAllParticipants(raceId);
    }

    public ParticipantLiveData getParticipant(String participantId) {
        return participantRepository.getParticipant(participantId);
    }

    public RaceLiveData getRace(String raceId) {
        return raceRepository.getRace(raceId);

    }

}
