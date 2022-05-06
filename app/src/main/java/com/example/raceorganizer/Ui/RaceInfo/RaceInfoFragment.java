package com.example.raceorganizer.Ui.RaceInfo;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.raceorganizer.Adapters.CheckpointAdapter;
import com.example.raceorganizer.Adapters.ModeratorAdapter;
import com.example.raceorganizer.Adapters.ParticipantAdapter;
import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.MainActivity;
import com.example.raceorganizer.R;

import java.util.ArrayList;

public class RaceInfoFragment extends Fragment {

    private RaceInfoViewModel viewModel;
    private RecyclerView recyclerView;

    private CheckpointAdapter checkpointAdapter;
    private ParticipantAdapter participantAdapter;
    private ModeratorAdapter moderatorAdapter;

    private Race race;

    View view;

    TextView raceName;
    TextView raceDate;
    TextView starting;
    TextView ending;
    TextView amountOfPeople;
    ImageButton delete;

    Button checkpoint;
    Button participant;
    Button moderator;


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_race_info, container, false);

        setupRecycleViews(view);

        setup();

        setupButtons();

        return view;
    }

    public void setupRecycleViews(View view){
        viewModel = new ViewModelProvider(this).get(RaceInfoViewModel.class);
        viewModel.init();
        recyclerView = view.findViewById(R.id.checkpointRecycleList);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        race = viewModel.getRace(getArguments().getString("nameOfRace")).getValue();


        checkpointAdapter = new CheckpointAdapter(new ArrayList<Checkpoint>());
        participantAdapter = new ParticipantAdapter(race.getParticipants());
        moderatorAdapter = new ModeratorAdapter(race.getModerators());

        viewModel.getCheckpoints().observe(getViewLifecycleOwner(), x  -> {
            checkpointAdapter.set(x);
        });

        recyclerView.setAdapter(checkpointAdapter);
    }

    public void setupButtons(){
        delete.setOnClickListener(o ->  {
            ((MainActivity)this.getActivity()).navController.navigate(R.id.list_of_races);
        });
        checkpoint.setOnClickListener(o ->  {
            moderator.setBackgroundColor(getResources().getColor(R.color.green));
            participant.setBackgroundColor(getResources().getColor(R.color.green));
            checkpoint.setBackgroundColor(getResources().getColor(R.color.black));
            recyclerView.setAdapter(checkpointAdapter);
        });
        participant.setOnClickListener(o ->  {
            moderator.setBackgroundColor(getResources().getColor(R.color.green));
            checkpoint.setBackgroundColor(getResources().getColor(R.color.green));
            participant.setBackgroundColor(getResources().getColor(R.color.black));
            recyclerView.setAdapter(participantAdapter);
        });
        moderator.setOnClickListener(o ->  {
            checkpoint.setBackgroundColor(getResources().getColor(R.color.green));
            participant.setBackgroundColor(getResources().getColor(R.color.green));
            moderator.setBackgroundColor(getResources().getColor(R.color.black));
            recyclerView.setAdapter(moderatorAdapter);
        });
    }

    public void setup(){
        raceName = view.findViewById(R.id.raceName);
        raceDate = view.findViewById(R.id.dateOfRace);
        starting = view.findViewById(R.id.startingTime);
        ending = view.findViewById(R.id.endingTime);
        amountOfPeople = view.findViewById(R.id.amountOfPeople);
        raceName.setText(race.getName());
        raceDate.setText(race.getDate());
        starting.setText(race.getStart());
        ending.setText(race.getEnd());
        amountOfPeople.setText(Integer.toString(race.getParticipantsAmount()));

        delete = view.findViewById(R.id.garbageCan);
        checkpoint = view.findViewById(R.id.checkpointButton);
        participant = view.findViewById(R.id.participantButton);
        moderator = view.findViewById(R.id.moderatorButton);

        checkpoint.setBackgroundColor(getResources().getColor(R.color.black));
    }

}