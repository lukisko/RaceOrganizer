package com.example.raceorganizer.ui.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.raceorganizer.R;
import com.example.raceorganizer.MainActivity;

public class Home extends Fragment {


    NavController navController;
    Button organize;
    Button participate;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        organize = view.findViewById(R.id.organize);
        participate = view.findViewById(R.id.participate);

        organize.setOnClickListener(o -> ((MainActivity)this.getActivity()).navController.navigate(R.id.login_register));
        participate.setOnClickListener(o -> ((MainActivity)this.getActivity()).navController.navigate(R.id.list_of_races));

        return view;
    }

}