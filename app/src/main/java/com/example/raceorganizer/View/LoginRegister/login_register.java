package com.example.raceorganizer.View.LoginRegister;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.raceorganizer.R;
import com.example.raceorganizer.View.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class login_register extends Fragment {

    View view;
    FloatingActionButton next;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login_register, container, false);

        next = view.findViewById(R.id.next);
        next.setOnClickListener(o -> ((MainActivity)this.getActivity()).navController.navigate(R.id.list_of_races));

        return view;
    }
}