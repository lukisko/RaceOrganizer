package com.example.raceorganizer.ui.Races;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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


public class ListOfRaces extends Fragment {
    private RecyclerView raceList;
    private RaceAdapter raceAdapter;
    private ArrayList<Race> races;

    View view;
    FloatingActionButton add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_of_races, container, false);

        testRaceList();

        add = view.findViewById(R.id.addRace);
        add.setOnClickListener(o -> ((MainActivity)this.getActivity()).navController.navigate(R.id.nav_add_race));

//        raceAdapter.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
//                // toggle clicked cell state
//                Toast.makeText(getActivity(),"Text!",Toast.LENGTH_SHORT).show();
//                // register in adapter that state for selected cell is toggled
//
//            }
//        });



        return view;
    }

    private void testRaceList(){

        raceList = view.findViewById(R.id.recicleView);
        raceList.hasFixedSize();
        raceList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        races = new ArrayList<>();
        for(int i=0; i<200; i++) {
            races.add(new Race("marathon zilina"));
            races.add(new Race("telovichovna jednota stranik"));
        }


        raceAdapter = new RaceAdapter(races);
        raceAdapter.setOnClickListener(o ->  {
            Toast.makeText(getActivity(), o.getName(), Toast.LENGTH_SHORT).show();
        });
        raceList.setAdapter(raceAdapter);
    }
}