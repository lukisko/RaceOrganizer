package com.example.raceorganizer.ui.addRaceFragment;

import android.os.Bundle;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Data.Model.RaceType;
import com.example.raceorganizer.MainActivity;
import com.example.raceorganizer.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


public class AddRaceFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    View view;
    EditText raceName;
    EditText startTime;
    EditText endTime;
    EditText raceDate;
    EditText raceCategory;
    FloatingActionButton addRaceButton;
    AddRaceViewModel addRaceViewModel;
    boolean isAllFieldsChecked = false;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_addrace, container, false);

        raceName = view.findViewById(R.id.race_name);
        startTime = view.findViewById(R.id.race_start_time);
        endTime = view.findViewById(R.id.race_end_time);
        raceDate = view.findViewById(R.id.race_date);
        raceCategory = view.findViewById(R.id.race_category);
        addRaceButton = view.findViewById(R.id.race_add_button);

        addRaceViewModel = AddRaceViewModel.getInstance();
        Spinner categorySpinner = view.findViewById(R.id.categorySpinner);
        categorySpinner.setOnItemSelectedListener(this);

        addRaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked = CheckAllFields();

                if (isAllFieldsChecked) {
                    addRace();
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.list_of_races);
                }
            }
        });
        return view;
    }

    public void addRace() {
        // Add the race
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date dateToAdd = null;
        try {
            dateToAdd = dateFormat.parse(String.valueOf(raceDate));
        } catch (ParseException e) {
            raceDate.setError("Invalid format,try dd/MM/yyyy");
        }
        Race raceToAdd = new Race("");
        raceToAdd.setName(raceName.getText().toString());
        raceToAdd.setStartTime(Integer.parseInt(startTime.getText().toString()));
        raceToAdd.setEndTime(Integer.parseInt(endTime.getText().toString()));
        raceToAdd.setDate(dateToAdd);
        //may be changed due to choosing which type of race is desired
        raceToAdd.setRaceType(RaceType.Bicycling);
        addRaceViewModel.addRace(raceToAdd);
    }


    private boolean CheckAllFields() {
        if (raceName.length() == 0) {
            raceName.setError("Race name field is required");
            return false;
        }

        if (startTime.length() == 0) {
            startTime.setError("Start time is required");
            return false;
        } else {
            if (startTime.getInputType() != InputType.TYPE_CLASS_DATETIME) {
                startTime.setError("Start time input should be of time format");
                return false;
            }
        }

        if (endTime.length() == 0) {
            endTime.setError("End time is required");
            return false;
        } else {
            if (endTime.getInputType() != InputType.TYPE_CLASS_DATETIME) {
                endTime.setError("End time input should be of time format");
                return false;
            }
        }

        if (raceDate.length() == 0) {
            raceDate.setError("Race date is required");
            return false;
        } else {
            if (raceDate.getInputType() != InputType.TYPE_CLASS_DATETIME) {
                raceDate.setError("This input type should be of date format");
                return false;
            }
        }
        if (raceCategory.length() == 0) {
            raceCategory.setError("Race category is required");
            return false;
        }

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}