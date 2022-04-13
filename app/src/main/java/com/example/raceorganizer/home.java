package com.example.raceorganizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class home extends Fragment {


    NavController navController;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        Button organize = view.findViewById(R.id.organize);
        organize.setOnClickListener(o -> ((MainActivity)this.getActivity()).navController.navigate(R.id.login_register));
        return view;
    }

}