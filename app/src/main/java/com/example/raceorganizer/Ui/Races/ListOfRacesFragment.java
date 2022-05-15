package com.example.raceorganizer.Ui.Races;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

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


import com.example.raceorganizer.Adapters.RaceAdapter;
import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.R;
import com.example.raceorganizer.MainActivity;
import com.example.raceorganizer.Ui.Home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;


public class ListOfRacesFragment extends Fragment {
    RecyclerView recyclerView;
    RaceAdapter raceAdapter;

    private SharedPreferences sharedPreferences;
    private ListOfRacesViewModel viewModel;

    View view;
    FloatingActionButton add;
    private Button organizer;
    private Button moderator;

    private String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_of_races, container, false);
        sharedPreferences = getContext().getSharedPreferences("UserPref", MODE_PRIVATE);
        raceAdapter = new RaceAdapter(new ArrayList<>());

        viewModel = new ViewModelProvider(this).get(ListOfRacesViewModel.class);
        organizer = view.findViewById(R.id.organizer);
        moderator = view.findViewById(R.id.moderator);


        if (sharedPreferences.getBoolean(HomeFragment.PARTICIPANT_PREFERENCE, true)) { //should I assume user is participant or moderator if there is no info?
            organizer.setVisibility(View.INVISIBLE);
            moderator.setVisibility(View.INVISIBLE);

            viewModel.getCurrentUser().observe(this.getViewLifecycleOwner(), id -> {
                if (id != null) {
                    viewModel.getParticipant(id.getUid()).observe(getViewLifecycleOwner(), ids -> {
                        viewModel.getRaces(ids.getRaceIds()).observe(getViewLifecycleOwner(), races -> {
                            raceAdapter.set(races);
                        });
                    });
                    raceAdapter.setOnClickListener(o -> {
                        Bundle bundle = new Bundle();
                        bundle.putString("idOfRace", o.getId());
                        ((MainActivity) this.getActivity()).navController.navigate(R.id.checkpointListPFragment, bundle);
                    });
                }
            });
        } else {
            organizer.setVisibility(View.VISIBLE);
            organizer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setCreatorList();
                }
            });
            moderator.setVisibility(View.VISIBLE);
            moderator.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setModeratorList();
                }
            });
        }


        recyclerView = view.findViewById(R.id.raceListRecicleView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));






        recyclerView.setAdapter(raceAdapter);


        add = view.findViewById(R.id.addRace);
        add.setOnClickListener(o -> {
            if (sharedPreferences.getBoolean(HomeFragment.PARTICIPANT_PREFERENCE, true)) { //should I assume user is participant or moderator if there is no info?
                handleAddingParticipantToRace();
                //((MainActivity)this.getActivity()).navController.navigate(R.id.barCodeFragment);
            } else {
                ((MainActivity) this.getActivity()).navController.navigate(R.id.nav_add_race);
            }

        });

        return view;
    }

    private void handleAddingParticipantToRace() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity());
        intentIntegrator.setPrompt("Scan a barcode or QR Code");
        intentIntegrator.setOrientationLocked(true);
        ArrayList<String> barCodeTypes = new ArrayList<>();
        barCodeTypes.add("QR_CODE");
        intentIntegrator.initiateScan(barCodeTypes);
    }

    public void setCreatorList(){
        viewModel.getCurrentUser().observe(this.getViewLifecycleOwner(), id -> {
            viewModel.getAllRaces(id.getUid()).observe(getViewLifecycleOwner(), races -> {
                raceAdapter.set(races);
            });
        });
        raceAdapter.setOnClickListener(o -> {
            Bundle bundle = new Bundle();
            bundle.putString("idOfRace", o.getId());
            ((MainActivity) this.getActivity()).navController.navigate(R.id.race_info, bundle);
        });
    }
    public void setModeratorList(){
        viewModel.getCurrentUser().observe(this.getViewLifecycleOwner(), id -> {
            viewModel.getCheckpointsByModerator(id.getUid()).observe(getViewLifecycleOwner(), checkpoints -> {
                ArrayList<String> ids = new ArrayList<>();
                for (int i = 0; i < checkpoints.size(); i++) {
                    ids.add(checkpoints.get(i).getId());
                }
                if(ids.size() > 0){
                    viewModel.getRaces(ids).observe(getViewLifecycleOwner(),races -> {
                        raceAdapter.set(races);
                    });
                }
            });
        });
        raceAdapter.setOnClickListener(o -> {
            Bundle bundle = new Bundle();
            bundle.putString("idOfRace", o.getId());
//            ((MainActivity) this.getActivity()).navController.navigate(R.id.checkpoint, bundle);
        });
    }

}