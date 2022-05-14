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
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.raceorganizer.Data.Model.Participant;
import com.example.raceorganizer.MainActivity;
import com.example.raceorganizer.R;
import com.example.raceorganizer.Ui.Home.HomeFragment;
import com.google.firebase.Timestamp;

import java.util.Date;

public class AddParticipantView extends Fragment {
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String PARTICIPANT_ID = "participant_id";
    static final String AGE = "age";
    private AddParticipantViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private String raceId;
    private LiveData<Participant> liveParticipant;

    Toast incorrectInfo;

    View view;

    EditText firstName;
    EditText lastName ;
    EditText age;
    EditText number;
    ProgressBar spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //setting everything up
        view = inflater.inflate(R.layout.fragment_addparticipant, container, false);

        sharedPreferences = view.getContext().getSharedPreferences("UserPref",MODE_PRIVATE);

        viewModel = new ViewModelProvider(this).get(AddParticipantViewModel.class);

        viewModel = new ViewModelProvider(this).get(AddParticipantViewModel.class);

        raceId = getArguments().getString("idOfRace");

        firstName = view.findViewById(R.id.participantFirstName);
        lastName = view.findViewById(R.id.participantLastName);
        age = view.findViewById(R.id.participantAge);
        number = view.findViewById(R.id.participantNumber);
        spinner = view.findViewById(R.id.progressBar1);

        // set the name and create user if does not exist if the user is participant
        if (isParticipantLog()){
            if (sharedPreferences.contains(FIRST_NAME)){
                firstName.setText(sharedPreferences.getString(FIRST_NAME,""));
            }
            if (sharedPreferences.contains(LAST_NAME)){
                lastName.setText(sharedPreferences.getString(LAST_NAME,""));
            }
        }

        Button createButton = view.findViewById(R.id.addParticipant);
        createButton.setOnClickListener((v)->{
            Log.i("preferences","heeereee");
            createParticipant();
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

    public boolean isParticipantLog(){
        return sharedPreferences.contains(HomeFragment.PARTICIPANT_PREFERENCE) && sharedPreferences.getBoolean(HomeFragment.PARTICIPANT_PREFERENCE,true);
    }

    private void createParticipant(){
        //check if the number and age is really a number
        int tempAge;
        try{
            tempAge = Integer.parseInt(age.getText().toString());
        } catch (Exception e){
            age.setError("enter your age as whole number");
            return;
        }
        int tempNumber;
        try{
            tempNumber = Integer.parseInt(number.getText().toString());
        } catch (Exception e){
            number.setError("enter your racing number");
            return;
        }

        //check if we expect user to be already created
        String participantId;
        if (isParticipantLog() && sharedPreferences.contains(PARTICIPANT_ID)){
            Log.i("addParticipant",sharedPreferences.getString(PARTICIPANT_ID,"no Id so far"));
            // the case when there is user and that he already have a Participant id
            participantId = sharedPreferences.getString(PARTICIPANT_ID,"");
            viewModel.putParticipantToRace(raceId,participantId);
            ((MainActivity)this.getActivity()).navController.popBackStack();
        } else {
            spinner.setVisibility(View.VISIBLE);
            Participant newPart = new Participant(
                    "",
                    firstName.getText().toString(),
                    lastName.getText().toString(),
                    tempAge,
                    tempNumber,
                    0,
                    new Timestamp(new Date(0,0,0))
            );
            liveParticipant = viewModel.createParticipant(newPart);

            liveParticipant.observe(getViewLifecycleOwner(), (Participant part) -> {
                if (isParticipantLog()){
                    //in case that user is registering himself safe the participant id
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(PARTICIPANT_ID, part.getId());
                    editor.apply();
                    Log.i("addParticipant","id: "+part.getId());
                }

                spinner.setVisibility(View.INVISIBLE);
                viewModel.addParticipant(part, raceId);
                ((MainActivity)this.getActivity()).navController.popBackStack();
            });
            return;
        }
    }
}
