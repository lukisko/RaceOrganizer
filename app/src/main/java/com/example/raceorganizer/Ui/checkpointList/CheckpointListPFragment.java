package com.example.raceorganizer.Ui.checkpointList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.raceorganizer.Adapters.CheckpointContentAdapter;
import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.R;


import java.util.ArrayList;
import java.util.Objects;

public class CheckpointListPFragment extends Fragment {
    private CheckpointListPViewModel viewModel;

    View view;


    LiveData<Race> raceLive;

    TextView raceName;
    TextView raceDate;
    TextView raceStartTime;
    TextView raceEndTime;

    RecyclerView checkpointRecycler;
    CheckpointContentAdapter checkpointAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_checkpointlist_participant, container, false);

        viewModel = new ViewModelProvider(this).get(CheckpointListPViewModel.class);

        raceLive = viewModel.getRace(getArguments().getString("idOfRace"));


        checkpointRecycler = view.findViewById(R.id.checkpointList);
        raceName = view.findViewById(R.id.raceName);
        raceDate = view.findViewById(R.id.raceDate);
        raceStartTime = view.findViewById(R.id.raceStartTime);
        raceEndTime = view.findViewById(R.id.raceEndTime);

        checkpointRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));

        checkpointAdapter = new CheckpointContentAdapter(viewModel.getCheckpoints().getValue());
        checkpointRecycler.setAdapter(checkpointAdapter);


        raceLive.observe(getViewLifecycleOwner(), (Race race) -> {
            Log.i("checkpointListPFragment", "I got " + race.getCheckpoints().size() + " races from DB");
            raceName.setText(race.getName());
            raceDate.setText(race.getDate());
            raceStartTime.setText(race.getStart());
            raceEndTime.setText(race.getEnd());
            if (race.getCheckpoints().size() < 1) {
                ArrayList<Checkpoint> chkpnst = new ArrayList<>();
                chkpnst.add(new Checkpoint("no checkpoints visited"));
                checkpointAdapter.set(chkpnst);
            } else {
                checkpointAdapter.set(race.getCheckpoints());
            }
        });
        Race tmpRace = raceLive.getValue();
        if (tmpRace != null) {
            checkpointAdapter.set(tmpRace.getCheckpoints());
        } else {
            Log.i("checkpointListPFragment", "I am at the empty part");
            ArrayList<Checkpoint> myCheckpoints = new ArrayList<>();
            myCheckpoints.add(new Checkpoint("no checkpoints visited"));
            checkpointAdapter.set(myCheckpoints);
        }

        return view;

    }
}
