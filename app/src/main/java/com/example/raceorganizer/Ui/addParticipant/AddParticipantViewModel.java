package com.example.raceorganizer.Ui.addParticipant;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.LiveData.Participant.ParticipantLiveData;
import com.example.raceorganizer.Data.Model.Participant;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Repository.ParticipantRepository;
import com.example.raceorganizer.Repository.RaceRepository;

import java.util.ArrayList;

public class AddParticipantViewModel extends ViewModel {
    private ParticipantRepository participantRepository;
    private RaceRepository raceRepository;

    public AddParticipantViewModel(){
        participantRepository = ParticipantRepository.getInstance();
        raceRepository = RaceRepository.getInstance();
    }

    public void addParticipant(Participant participant, String raceId){
        ArrayList<String> tempArr = participant.getRaceIds();
        tempArr.add(raceId);
        participant.setRaceIds(tempArr);
        participantRepository.addParticipant(participant);
    }

    public LiveData<Participant> createParticipant(Participant participant){
        return participantRepository.addParticipant(participant);
    }

    public void putParticipantToRace(String raceId,String participantId){
        participantRepository.addRace(raceId,participantId);
    }
}
