package com.example.raceorganizer.Ui.checkpointRaceParticipants;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raceorganizer.Adapters.CheckpointAdapter;
import com.example.raceorganizer.Adapters.CheckpointParticipantAdapter;
import com.example.raceorganizer.Adapters.ParticipantAdapter;
import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Participant;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.R;
import com.example.raceorganizer.Ui.checkpointList.CheckpointListPViewModel;

import java.util.ArrayList;


public class CheckpointParticipantsFragment extends Fragment {
    private View view;
    private CheckpointRaceParticipantsViewModel viewModel;
    private LiveData<ArrayList<Participant>> participantLive;
    private LiveData<Race> raceLive;
    private LiveData<Checkpoint> checkpointLive;
    private Participant participant;

    private TextView checkpointName;
    private TextView raceStartTime;
    private TextView raceEndTime;
    private EditText et_searchParticipant;
    private ImageButton searchButton;

    private RecyclerView participantRecycler;
    private ParticipantAdapter participantAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_checkpointlist_participant, container, false);
        viewModel = new ViewModelProvider(this).get(CheckpointRaceParticipantsViewModel.class);

        participantLive = viewModel.getParticipants(getArguments().getString("idOfRace"));
        raceLive = viewModel.getRace(getArguments().getString("idOfRace"));
        checkpointLive=viewModel.getCheckpoint(getArguments().getString("idOfRace"));

        participantRecycler = view.findViewById(R.id.participantList);

        checkpointName = view.findViewById(R.id.et_checkpoint_name_label);
        raceStartTime = view.findViewById(R.id.start_time_label);
        raceEndTime = view.findViewById(R.id.end_time_label);
        et_searchParticipant = view.findViewById(R.id.et_search_participant);
        searchButton = view.findViewById(R.id.bt_search_button);

        //
        participantLive.observe(getViewLifecycleOwner(), (Participant participant) -> {
            // participant first name
            //participant last name
            //participant number
            checkpointName.setText(checkpoint.getName());
            raceDate.setText(race.getDate());
            raceStartTime.setText(race.getStart());
            raceEndTime.setText(race.getEnd());
        });
        raceLive.observe(getViewLifecycleOwner(), (Race race) -> {
            raceStartTime.setText(race.getStart());
            raceEndTime.setText(race.getEnd());
        });



        participantRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));

        participantAdapter = new CheckpointAdapter(viewModel.getCheckpoints().getValue());
        participantRecycler.setAdapter(participantAdapter);

        viewModel.getCheckpoints().observe(getViewLifecycleOwner(), (ArrayList<Checkpoint> checks) -> {
            checkpointAdapter.set(checks);
        });
        return view;
    }
}
