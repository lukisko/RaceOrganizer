package com.example.raceorganizer.Ui.addCheckpoint;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;


import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.MainActivity;
import com.example.raceorganizer.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddCheckpointView extends Fragment {

    private AddCheckpointViewModel viewModel;

    View view;
    EditText chName;
    EditText points;
    SwitchCompat hasPoints;
    EditText location;
    EditText moderator;
    EditText description;
    FloatingActionButton createCheckpointBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_checkpoint, container, false);

        viewModel = new ViewModelProvider(this).get(AddCheckpointViewModel.class);
        viewModel = new ViewModelProvider(this).get(AddCheckpointViewModel.class);

        chName = view.findViewById(R.id.newCheckpointName);
        points = view.findViewById(R.id.newCheckpointPoints);
        hasPoints = view.findViewById(R.id.switch1);
        location = view.findViewById(R.id.newCheckpointLocation);
        moderator = view.findViewById(R.id.moderatorNickname);
        description = view.findViewById(R.id.newCheckpointDescription);
        hasPoints.setOnClickListener(v -> {
            if (hasPoints.isChecked()) {
                points.setEnabled(true);
                points.setVisibility(View.VISIBLE);
            } else {
                points.setEnabled(false);
                points.setVisibility(View.INVISIBLE);
            }
        });
        createCheckpointBtn = view.findViewById(R.id.addCheckpoint);
        createCheckpointBtn.setOnClickListener(v -> {
            String raceId = getArguments().getString("idOfRace");

            Checkpoint newCheck = new Checkpoint();
            newCheck.setName(chName.getText().toString());
            newCheck.setRaceId(raceId);
            if(hasPoints.getShowText()) {
                newCheck.setTotalPoints(Integer.parseInt(points.getText().toString()));
            }
            String[] moderators = moderator.getText().toString().split(",");

            for (String id:moderators) {
                newCheck.getModerators().add(id);
            }

            viewModel.makeCheckpoint(raceId, newCheck);
            Bundle bundle = new Bundle();
            bundle.putString("idOfRace", raceId);
            ((MainActivity) this.getActivity()).navController.navigate(R.id.race_info, bundle);
        });
        return view;
    }
}
