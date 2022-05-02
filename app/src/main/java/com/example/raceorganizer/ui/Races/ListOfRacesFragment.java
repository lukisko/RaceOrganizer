package com.example.raceorganizer.ui.Races;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.R;
import com.example.raceorganizer.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;



public class ListOfRacesFragment extends Fragment {
     RecyclerView recyclerView;
     RaceAdapter raceAdapter;

    private ListOfRacesViewModel listOfRacesViewModel;

    View view;
    FloatingActionButton add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_of_races, container, false);


        listOfRacesViewModel = new ViewModelProvider(this).get(ListOfRacesViewModel.class);
        recyclerView = view.findViewById(R.id.raceListRecicleView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        raceAdapter = new RaceAdapter(listOfRacesViewModel.getAllRaces().getValue());
        raceAdapter.setOnClickListener(o ->  {
            Bundle bundle = new Bundle();
            bundle.putString("nameOfRace",o.getName());
            ((MainActivity)this.getActivity()).navController.navigate(R.id.race_info,bundle);
        });
        recyclerView.setAdapter(raceAdapter);


        add = view.findViewById(R.id.addRace);
//        add.setOnClickListener(o -> ((MainActivity)this.getActivity()).navController.navigate(R.id.race_info));

        return view;
    }

}