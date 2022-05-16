package com.example.raceorganizer.Ui.Profile;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.raceorganizer.R;
import com.example.raceorganizer.Ui.login_activity.LoginViewModel;


public class ProfileFragment extends Fragment {

    private TextView id;
    private TextView firstName;
    private TextView lastName;
    private View view;
    private SharedPreferences sharedPreferences;
    private ProfileViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        id = view.findViewById(R.id.id);
        firstName = view.findViewById(R.id.FirstName);
        lastName = view.findViewById(R.id.LastName);

        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        if (true) {
            viewModel.getCurrentUser().observe(getViewLifecycleOwner(),x->{
                viewModel.getModerator(x.getUid()).observe(getViewLifecycleOwner(), m -> {
                    final int mid = x.getUid().length() / 2; //get the middle of the String
                    String[] parts = {x.getUid().substring(0, mid),x.getUid().substring(mid)};
                    id.setText(parts[0] + "\n"+ parts[1]);
                    firstName.setText(m.getFirstName());
                    lastName.setText(m.getLastName());
                });
            });
        }


        sharedPreferences = getContext().getSharedPreferences("UserPref", MODE_PRIVATE);
        return view;
    }
}