package com.example.raceorganizer.Ui.Races;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ListOfRacesFragment extends Fragment {
     RecyclerView recyclerView;
     RaceAdapter raceAdapter;

    private ListOfRacesViewModel viewModel;

    View view;
    FloatingActionButton add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_of_races, container, false);


        viewModel = new ViewModelProvider(this).get(ListOfRacesViewModel.class);
        viewModel.init("", "nickName");

        recyclerView = view.findViewById(R.id.raceListRecicleView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        raceAdapter = new RaceAdapter(new ArrayList<>());
        viewModel.getAllRaces().observe(getViewLifecycleOwner(), x  -> {
            raceAdapter.set(x);
        });

        raceAdapter.setOnClickListener(o ->  {
            Bundle bundle = new Bundle();
            bundle.putString("idOfRace",o.getId());
            ((MainActivity)this.getActivity()).navController.navigate(R.id.race_info,bundle);
        });
        recyclerView.setAdapter(raceAdapter);


        add = view.findViewById(R.id.addRace);
        add.setOnClickListener(o -> ((MainActivity)this.getActivity()).navController.navigate(R.id.nav_add_race));

        return view;
    }

}