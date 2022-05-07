package com.example.raceorganizer.ui.addParticipant;

import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.Model.Participant;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Repository.ParticipantRepository;
import com.example.raceorganizer.Repository.RaceRepository;

public class AddParticipantViewModel extends ViewModel {
    private ParticipantRepository participantRepository;
    private RaceRepository raceRepository;

    public AddParticipantViewModel(){
        participantRepository = ParticipantRepository.getInstance();
        raceRepository = RaceRepository.getInstance();
    }

    public void addParticipant(Participant participant, String raceName){
        participantRepository.addParticipant(raceRepository.getRace(raceName).getValue(), participant);
    }
}
