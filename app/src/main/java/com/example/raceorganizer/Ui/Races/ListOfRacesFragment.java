package com.example.raceorganizer.Ui.Races;

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


import com.example.raceorganizer.Adapters.RaceAdapter;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_of_races, container, false);


        viewModel = new ViewModelProvider(this).get(ListOfRacesViewModel.class);

        recyclerView = view.findViewById(R.id.raceListRecicleView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        sharedPreferences = getContext().getSharedPreferences("UserPref",MODE_PRIVATE);
        
        raceAdapter = new RaceAdapter(new ArrayList<>());
        viewModel.getAllRaces("nickName").observe(getViewLifecycleOwner(), x  -> {
            raceAdapter.set(x);
        });

        raceAdapter.setOnClickListener(o ->  {
            Bundle bundle = new Bundle();
            bundle.putString("idOfRace",o.getId());
            if (sharedPreferences.getBoolean(HomeFragment.PARTICIPANT_PREFERENCE,true)){ //should I assume user is participant or moderator if there is no info?
                ((MainActivity)this.getActivity()).navController.navigate(R.id.checkpointListPFragment,bundle);
            } else {
                ((MainActivity)this.getActivity()).navController.navigate(R.id.race_info,bundle);
            }
        });
        recyclerView.setAdapter(raceAdapter);


        add = view.findViewById(R.id.addRace);
       add.setOnClickListener(o -> {
           if (sharedPreferences.getBoolean(HomeFragment.PARTICIPANT_PREFERENCE,true)){ //should I assume user is participant or moderator if there is no info?
               handleAddingParticipantToRace();
               //((MainActivity)this.getActivity()).navController.navigate(R.id.barCodeFragment);
           } else {
               ((MainActivity)this.getActivity()).navController.navigate(R.id.nav_add_race);
           }

       });

        return view;
    }

    private void handleAddingParticipantToRace(){
        IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity());
        intentIntegrator.setPrompt("Scan a barcode or QR Code");
        intentIntegrator.setOrientationLocked(true);
        ArrayList<String> barCodeTypes = new ArrayList<>();
        barCodeTypes.add("QR_CODE");
        intentIntegrator.initiateScan(barCodeTypes);
    }

}