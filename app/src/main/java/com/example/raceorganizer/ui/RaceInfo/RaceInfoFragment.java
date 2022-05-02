package com.example.raceorganizer.ui.RaceInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.MainActivity;
import com.example.raceorganizer.R;
import com.example.raceorganizer.ui.Races.ListOfRacesViewModel;
import com.example.raceorganizer.ui.Races.RaceAdapter;

import java.util.ArrayList;

public class RaceInfoFragment extends Fragment {

    private RaceInfoViewModel viewModel;
    private RecyclerView recyclerView;
    private CheckpointAdapter checkpointAdapter;
    private Race race;


    View view;

    TextView raceName;
    TextView raceDate;
    TextView starting;
    TextView ending;
    TextView amountOfPeople;




    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_race_info, container, false);

        setupRecycleView(view);

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


        return view;
    }

    public void setupRecycleView(View view){
        viewModel = new ViewModelProvider(this).get(RaceInfoViewModel.class);
        recyclerView = view.findViewById(R.id.checkpointRecycleList);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        race = viewModel.getRace(getArguments().getString("nameOfRace")).getValue();
        checkpointAdapter = new CheckpointAdapter(race.getCheckpoints());
        checkpointAdapter.setOnClickListener(o ->  {
            Context context = view.getContext();
            CharSequence text = o.getName();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        });
        recyclerView.setAdapter(checkpointAdapter);
    }

}