package com.example.raceorganizer.Ui.addParticipant;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.raceorganizer.Data.Model.Participant;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.MainActivity;
import com.example.raceorganizer.R;


public class AddParticipantView extends Fragment {
    static String FIRST_NAME = "first_name";
    static final String LAST_NAME = "last_name";
    static final String AGE = "age";
    private AddParticipantViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private String raceName;

    View view;

    EditText firstName;
    EditText lastName ;
    EditText age;
    EditText number;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_addparticipant, container, false);

        sharedPreferences = view.getContext().getSharedPreferences("UserPref",MODE_PRIVATE);

        viewModel = new ViewModelProvider(this).get(AddParticipantViewModel.class);

        viewModel = new ViewModelProvider(this).get(AddParticipantViewModel.class);

        raceName = getArguments().getString("nameOfRace");

        firstName = view.findViewById(R.id.participantFirstName);
        lastName = view.findViewById(R.id.participantLastName);
        age = view.findViewById(R.id.participantAge);
        number = view.findViewById(R.id.participantNumber);

        if (sharedPreferences.contains(FIRST_NAME)){
            firstName.setText(sharedPreferences.getString(FIRST_NAME,""));
        }
        if (sharedPreferences.contains(LAST_NAME)){
            lastName.setText(sharedPreferences.getString(LAST_NAME,""));
        }



        Button createButton = view.findViewById(R.id.addParticipant);
        createButton.setOnClickListener((v)->{
            Log.i("preferences","heeereee");
            Participant participant = new Participant(
                    firstName.getText().toString(),
                    lastName.getText().toString(),
                    Integer.parseInt(age.getText().toString()),
                    Integer.parseInt(number.getText().toString()),
                    0,
                    0
            );
            viewModel.addParticipant(participant, getArguments().getString("nameOfRace"));
            Bundle bundle = new Bundle();
            bundle.putString("nameOfRace",raceName);
            ((MainActivity)this.getActivity()).navController.navigate(R.id.race_info,bundle);
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            editor.putString(FIRST_NAME,firstName.getText().toString());
            editor.putString(LAST_NAME, lastName.getText().toString());
            //editor.putInt(AGE,Integer.parseInt(age.getText().toString()));
        } catch (Exception e){
            Log.i("preferences", "unable to save preferences in add participant: "+e.getMessage());
        } finally {
            editor.apply();
        }

    }
}
