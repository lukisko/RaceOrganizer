package com.example.raceorganizer.Ui.assignPoints;


import androidx.lifecycle.ViewModel;
import com.example.raceorganizer.Data.LiveData.Checkpoint.CheckpointLiveData;
import com.example.raceorganizer.Data.LiveData.Participant.ParticipantLiveData;
import com.example.raceorganizer.Repository.CheckpointRepository;
import com.example.raceorganizer.Repository.ParticipantRepository;

public class AssignPointsViewModel extends ViewModel {

    private ParticipantRepository participantRepository;
    private CheckpointRepository checkpointRepository;

    public AssignPointsViewModel() {
        this.participantRepository = ParticipantRepository.getInstance();
        this.checkpointRepository = CheckpointRepository.getInstance();
    }


    public void assignPoints(String participantId, String checkpointId, String points, int totalPoints) {
        participantRepository.addCheckpoint(participantId, checkpointId, points, totalPoints);
    }
    public ParticipantLiveData getParticipant(String participantId)
    {
        return participantRepository.getParticipant(participantId);
    }
    public CheckpointLiveData getCheckpoint(String checkpointId)
    {
        return checkpointRepository.getCheckpoint(checkpointId);
    }


}
