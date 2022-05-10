package com.example.raceorganizer.ui.Home;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.raceorganizer.R;
import com.example.raceorganizer.MainActivity;

public class HomeFragment extends Fragment {
    public static final String PARTICIPANT_PREFERENCE = "participant";

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

        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("UserPref",MODE_PRIVATE);

        organize.setOnClickListener(o -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(PARTICIPANT_PREFERENCE, false);
            editor.apply();
            ((MainActivity)this.getActivity()).navController.navigate(R.id.login_register);
        });
        participate.setOnClickListener(o -> {
            ((MainActivity)this.getActivity()).navController.navigate(R.id.list_of_races);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(PARTICIPANT_PREFERENCE, true);
            editor.apply();
        });

        return view;
    }

}