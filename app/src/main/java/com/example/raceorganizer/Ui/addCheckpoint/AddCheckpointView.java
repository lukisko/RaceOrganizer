package com.example.raceorganizer.Ui.addCheckpoint;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;


import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.MainActivity;
import com.example.raceorganizer.R;
import com.example.raceorganizer.Ui.RaceInfo.RaceInfoViewModel;
import com.example.raceorganizer.Ui.addParticipant.AddParticipantViewModel;

public class AddCheckpointView extends Fragment {

    private AddCheckpointViewModel viewModel;

    View view;
    EditText chName;
    EditText points;
    SwitchCompat hasPoints;
    EditText location;
    EditText moderator;
    EditText description;
    Button createCheckpointBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_addcheckpoint, container, false);

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
        createCheckpointBtn = view.findViewById(R.id.createCheckpointBtn);
        createCheckpointBtn.setOnClickListener(v -> {
            Checkpoint newCheck = new Checkpoint(chName.getText().toString());
            String raceName = getArguments().getString("nameOfRace");
            try {
                viewModel.makeCheckpoint(raceName, newCheck);
                Bundle bundle = new Bundle();
                bundle.putString("nameOfRace", raceName);
                ((MainActivity) this.getActivity()).navController.navigate(R.id.race_info, bundle);
            } catch (NullPointerException e) {
                Log.i("navigation", "got null for name of race");
            }
        });
        return view;
    }
}
