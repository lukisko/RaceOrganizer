package com.example.raceorganizer.Ui.RaceInfo;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.example.raceorganizer.Adapters.CheckpointAdapter;
import com.example.raceorganizer.Adapters.ModeratorAdapter;
import com.example.raceorganizer.Adapters.ParticipantAdapter;
import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.ListType;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.MainActivity;
import com.example.raceorganizer.R;
import com.example.raceorganizer.Ui.Home.HomeFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RaceInfoFragment extends Fragment {

    private RaceInfoViewModel viewModel;
    private RecyclerView recyclerView;

    private CheckpointAdapter checkpointAdapter;
    private ParticipantAdapter participantAdapter;
    private ModeratorAdapter moderatorAdapter;


    private SharedPreferences sharedPreferences;

    private ListType listType;

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
    FloatingActionButton addButton;


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_race_info, container, false);
        sharedPreferences = getContext().getSharedPreferences("UserPref", MODE_PRIVATE);

        setup();
        setupRecycleViews(view);
        setupButtons();

        return view;
    }

    public void setupRecycleViews(View view) {
        viewModel = new ViewModelProvider(this).get(RaceInfoViewModel.class);

        recyclerView = view.findViewById(R.id.checkpointRecycleList);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        checkpointAdapter = new CheckpointAdapter(new ArrayList<>());
        participantAdapter = new ParticipantAdapter(new ArrayList<>());
        moderatorAdapter = new ModeratorAdapter(new ArrayList<>());


        viewModel.getRace(getArguments().getString("idOfRace")).observe(getViewLifecycleOwner(), r -> {
            setValues(r);
        });
        viewModel.getCheckpoints(getArguments().getString("idOfRace")).observe(getViewLifecycleOwner(), x -> {
            checkpointAdapter.set(x);
        });
        viewModel.getParticipants(getArguments().getString("idOfRace")).observe(getViewLifecycleOwner(), x -> {
            participantAdapter.set(x);
        });
        viewModel.getCheckpoints(getArguments().getString("idOfRace")).observe(getViewLifecycleOwner(), x -> {
            ArrayList<String> ids = new ArrayList<>();
            for (Checkpoint c:x) {
                if(c.getModerators() != null){
                    ids.addAll(c.getModerators());
                }
            }
            if(ids.size()>0) {
                viewModel.getModerators(ids).observe(getViewLifecycleOwner(), m -> {
                    System.out.println(m.size());
                    moderatorAdapter.set(m);
                });
            }
        });

        setupRecycleViewsOnClick();

        recyclerView.setAdapter(checkpointAdapter);
    }

    public void setupRecycleViewsOnClick(){
        Context context = getContext();
        int duration = Toast.LENGTH_SHORT;
        checkpointAdapter.setOnClickListener( c -> {
            if (sharedPreferences.getBoolean(HomeFragment.PARTICIPANT_PREFERENCE, true)) {
                Toast.makeText(context, "participant" + " " + c.getId(), duration).show();
            }
            else{
                Toast.makeText(context, "registerd" + " " + c.getId(), duration).show();
            }
        });
    }

    public void setupButtons() {
        delete.setOnClickListener(o -> {
            viewModel.deleteRace(getArguments().getString("idOfRace"));
            ((MainActivity) this.getActivity()).navController.navigate(R.id.list_of_races);
        });
        checkpoint.setOnClickListener(o -> {
            moderator.setBackgroundColor(getResources().getColor(R.color.green));
            participant.setBackgroundColor(getResources().getColor(R.color.green));
            checkpoint.setBackgroundColor(getResources().getColor(R.color.black));
            listType = ListType.CHECKPOINTS;
            addButton.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(checkpointAdapter);
        });
        participant.setOnClickListener(o -> {
            moderator.setBackgroundColor(getResources().getColor(R.color.green));
            checkpoint.setBackgroundColor(getResources().getColor(R.color.green));
            participant.setBackgroundColor(getResources().getColor(R.color.black));
            listType = ListType.PARTICIPANTS;
            addButton.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(participantAdapter);
        });
        moderator.setOnClickListener(o -> {
            checkpoint.setBackgroundColor(getResources().getColor(R.color.green));
            participant.setBackgroundColor(getResources().getColor(R.color.green));
            moderator.setBackgroundColor(getResources().getColor(R.color.black));
            listType = ListType.MODERATORS;
            addButton.setVisibility(View.INVISIBLE);
            recyclerView.setAdapter(moderatorAdapter);
        });
        addButton.setOnClickListener((v) -> {
            Bundle bundle = new Bundle();
            bundle.putString("idOfRace", getArguments().getString("idOfRace"));
            bundle.putString("nameOfRace",race.getName());
            bundle.putString("idOfRace",race.getId());

            switch (listType) {
                case CHECKPOINTS:
                    ((MainActivity) this.getActivity()).navController.navigate(R.id.addCheckpointView2, bundle);
                    break;
                case PARTICIPANTS:
                    ((MainActivity) this.getActivity()).navController.navigate(R.id.addParticipantView, bundle);
                    break;
                default:
                    ((MainActivity) this.getActivity()).navController.navigate(R.id.list_of_races, bundle);
            }
        });
    }

    public void setup() {
        raceName = view.findViewById(R.id.raceName);
        raceDate = view.findViewById(R.id.dateOfRace);
        starting = view.findViewById(R.id.startingTime);
        ending = view.findViewById(R.id.endingTime);
        amountOfPeople = view.findViewById(R.id.amountOfPeople);


        delete = view.findViewById(R.id.garbageCan);
        checkpoint = view.findViewById(R.id.checkpointButton);
        participant = view.findViewById(R.id.participantButton);
        moderator = view.findViewById(R.id.moderatorButton);
        addButton = view.findViewById(R.id.addRace);

        listType = ListType.CHECKPOINTS;

        checkpoint.setBackgroundColor(getResources().getColor(R.color.black));
    }

    public void setValues(Race race) {
        raceName.setText(race.getName());
        raceDate.setText(race.getDate());
        starting.setText(race.getStart());
        ending.setText(race.getEnd());
        amountOfPeople.setText(Integer.toString(race.getParticipantsAmount()));
    }

}