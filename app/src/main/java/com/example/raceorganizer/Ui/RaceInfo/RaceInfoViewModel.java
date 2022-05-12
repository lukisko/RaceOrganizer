package com.example.raceorganizer.Ui.RaceInfo;

import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.LiveData.Checkpoint.CheckpointsLiveData;

import com.example.raceorganizer.Data.LiveData.Moderator.ModeratorsLiveData;
import com.example.raceorganizer.Data.LiveData.Participant.ParticipantsLiveData;
import com.example.raceorganizer.Data.LiveData.Race.RaceLiveData;
import com.example.raceorganizer.Data.Model.RegisteredUser;
import com.example.raceorganizer.Repository.CheckpointRepository;
import com.example.raceorganizer.Repository.LoggedInUserRepository;
import com.example.raceorganizer.Repository.ModeratorRepositori;
import com.example.raceorganizer.Repository.ParticipantRepository;
import com.example.raceorganizer.Repository.RaceRepository;

import java.util.ArrayList;

public class RaceInfoViewModel extends ViewModel {
    private RaceRepository raceRepository;
    private CheckpointRepository checkpointRepository;
    private ParticipantRepository participantRepository;
    private ModeratorRepositori moderatorRepositori;


    public RaceInfoViewModel(){
        checkpointRepository = CheckpointRepository.getInstance();
        raceRepository = RaceRepository.getInstance();
        participantRepository = ParticipantRepository.getInstance();
        moderatorRepositori = ModeratorRepositori.getInstance();
    }


    public RaceLiveData getRace(String id){
        return raceRepository.getRace(id);
    }

    public CheckpointsLiveData getCheckpoints(String raceId){
        return checkpointRepository.getCheckpoints(raceId);
    }

    public void deleteRace(String id){
        raceRepository.deleteRace(id);
    }
    public ParticipantsLiveData getParticipants(String raceId){
        return participantRepository.getAllParticipants(raceId);
    }
    public ModeratorsLiveData getModerators(ArrayList<String> moderatorIds){
        return moderatorRepositori.getModerators(moderatorIds);
    }
}
