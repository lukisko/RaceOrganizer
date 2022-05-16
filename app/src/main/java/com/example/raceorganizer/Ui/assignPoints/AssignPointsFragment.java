package com.example.raceorganizer.Ui.assignPoints;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Participant;
import com.example.raceorganizer.MainActivity;
import com.example.raceorganizer.R;
import com.example.raceorganizer.Ui.checkpointRaceParticipants.CheckpointRaceParticipantsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AssignPointsFragment extends Fragment{
    private View view;
    private String participantId;
    private String checkpointId;
    private FloatingActionButton plusButton;
    private FloatingActionButton minusButton;
    private TextView availablePoints;
    private TextView assignedpoints;
    private Button assignbutton;
    private AssignPointsViewModel assignPointsViewModel;
    private int checkpointTotalPoints;
    private int participantAssignedPoints = 0;



    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_moderator_assign_points, container, false);
        assignPointsViewModel = new ViewModelProvider(this).get(AssignPointsViewModel.class);


        participantId = getArguments().getString("idOfParticipant");
        checkpointId = getArguments().getString("idOfCheckpoint");

        plusButton = view.findViewById(R.id.moderator_add_points_button);
        minusButton = view.findViewById(R.id.moderator_subtract_points_button);
        availablePoints = view.findViewById(R.id.moderator_available_points);
        assignedpoints = view.findViewById(R.id.moderator_assigned_points);
        assignbutton = view.findViewById(R.id.assign_points);

        assignPointsViewModel.getCheckpoint(checkpointId).observe(getViewLifecycleOwner(), (Checkpoint checkpoint) -> {
            availablePoints.setText(String.valueOf(checkpoint.getTotalPoints()));
            checkpointTotalPoints = checkpoint.getTotalPoints();
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPoints();
            }
        });
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subtractPoints();
            }
        });
        assignbutton.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("idOfParticipant", participantId);
            bundle.putString("idOfCheckpoint", checkpointId);
            assignPointsViewModel.assignPoints(participantId, checkpointId, String.valueOf(participantAssignedPoints));
            ((MainActivity) this.getActivity()).navController.navigate(R.id.checkpointRaceParticipants, bundle);

        });

        return view;
    }


    private void addPoints() {
        if (participantAssignedPoints < checkpointTotalPoints) {
            participantAssignedPoints++;
            assignedpoints.setText(String.valueOf(participantAssignedPoints));
        }
    }

    private void subtractPoints() {
        if (participantAssignedPoints > 0) {
            participantAssignedPoints--;
            assignedpoints.setText(String.valueOf(participantAssignedPoints));
        }
    }

}
