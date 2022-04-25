package com.example.raceorganizer.View.RaceInfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.R;

import java.util.ArrayList;

public class race_info extends Fragment {

    private RecyclerView checkpointList;
    private CheckpointAdapter checkpointAdapter;
    private ArrayList<Checkpoint> checkpoints;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_race_info, container, false);

        testCheckpointList();

        return view;
    }

    private void testCheckpointList(){

        checkpointList = view.findViewById(R.id.recicleView);
        checkpointList.hasFixedSize();
        checkpointList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        checkpoints = new ArrayList<>();
        for(int i=0; i<200; i++) {
            checkpoints.add(new Checkpoint("a"));
            checkpoints.add(new Checkpoint("b"));
        }


        checkpointAdapter = new CheckpointAdapter(checkpoints);
        checkpointList.setAdapter(checkpointAdapter);
    }
}