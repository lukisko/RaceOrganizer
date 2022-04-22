package com.example.raceorganizer.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.raceorganizer.R;

public class home extends Fragment {


    NavController navController;
    Button organize;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        organize = view.findViewById(R.id.organize);
        organize.setOnClickListener(o -> ((MainActivity)this.getActivity()).navController.navigate(R.id.login_register));
        return view;
    }

}