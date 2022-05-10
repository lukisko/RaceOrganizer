package com.example.raceorganizer.Ui.checkpointList;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raceorganizer.Adapters.CheckpointAdapter;
import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.R;
import com.example.raceorganizer.Ui.checkpointList.CheckpointListPViewModel;


import java.util.ArrayList;

public class CheckpointListPFragment extends Fragment {
    private CheckpointListPViewModel viewModel;

    View view;

    Race race;

    TextView raceName;
    TextView raceDate;
    TextView raceStartTime;
    TextView raceEndTime;

    RecyclerView checkpointRecycler;
    CheckpointAdapter checkpointAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_checkpointlist_participant, container, false);

        viewModel = new ViewModelProvider(this).get(CheckpointListPViewModel.class);

        race  = viewModel.getRace(getArguments().getString("nameOfRace")).getValue();

        checkpointRecycler = view.findViewById(R.id.checkpointList);
        raceName = view.findViewById(R.id.raceName);
        raceDate = view.findViewById(R.id.raceDate);
        raceStartTime =view.findViewById(R.id.raceStartTime);
        raceEndTime = view.findViewById(R.id.raceEndTime);

        raceName.setText(race.getName());
        raceDate.setText(race.getDate());
        raceStartTime.setText(race.getStart());
        raceEndTime.setText(race.getEnd());

        checkpointRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));

        checkpointAdapter = new CheckpointAdapter(viewModel.getCheckpoints().getValue());
        checkpointRecycler.setAdapter(checkpointAdapter);

        viewModel.getCheckpoints().observe(getViewLifecycleOwner(),(ArrayList<Checkpoint> checks)->{
            checkpointAdapter.set(checks);
        });

        return view;

    }
}