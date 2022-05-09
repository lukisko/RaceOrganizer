package com.example.raceorganizer.ui.Home;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.raceorganizer.R;
import com.example.raceorganizer.MainActivity;
import com.example.raceorganizer.ui.login_activity.AuthenticationActivity;

public class HomeFragment extends Fragment {

    public static final String PARTICIPANT_PREFERENCE = "participant";
    Button organize;
    Button participate;
    Intent loginActivity;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActivityResultLauncher<Intent> activityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(), result -> {
                            //Toast.makeText(getContext(), result.getData().getAction(), Toast.LENGTH_LONG).show();
                            SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("UserPref", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            if (result.getData() != null) {
                                String uid = result.getData().getExtras().getString("user");
                                Log.i("User logged in", uid);
                                editor.putBoolean(PARTICIPANT_PREFERENCE, false);
                                ((MainActivity) this.getActivity()).navController.navigate(R.id.list_of_races);
                            } else {
                                editor.putBoolean(PARTICIPANT_PREFERENCE, true);
                            }
                            editor.apply();
                        });
        view = inflater.inflate(R.layout.fragment_home, container, false);
        organize = view.findViewById(R.id.organize);
        participate = view.findViewById(R.id.participate);
        loginActivity = new Intent(getActivity(), AuthenticationActivity.class);
        organize.setOnClickListener(view -> {

            activityResultLauncher.launch(loginActivity);
        });
        participate.setOnClickListener(o -> ((MainActivity) this.getActivity()).navController.navigate(R.id.list_of_races));

        return view;
    }

}