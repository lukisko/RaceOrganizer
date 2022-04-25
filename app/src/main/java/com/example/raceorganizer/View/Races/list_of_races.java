package com.example.raceorganizer.View.Races;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.R;
import com.example.raceorganizer.View.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class list_of_races extends Fragment {
    private RecyclerView foodList;
    private RaceAdapter foodAdapter;
    private ArrayList<Race> foods;

    View view;
    FloatingActionButton add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_of_races, container, false);

        testRaceList();

        add = view.findViewById(R.id.addRace);
        add.setOnClickListener(o -> ((MainActivity)this.getActivity()).navController.navigate(R.id.race_info));

        return view;
    }

    private void testRaceList(){

        foodList = view.findViewById(R.id.recicleView);
        foodList.hasFixedSize();
        foodList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        foods = new ArrayList<>();
        for(int i=0; i<200; i++) {
            foods.add(new Race("marathon zilina"));
            foods.add(new Race("telovichovna jednota stranik"));
        }


        foodAdapter = new RaceAdapter(foods);
        foodList.setAdapter(foodAdapter);
    }
}