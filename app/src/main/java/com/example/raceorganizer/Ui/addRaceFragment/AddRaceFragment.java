package com.example.raceorganizer.Ui.addRaceFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.R;
import com.example.raceorganizer.Ui.login_activity.AuthenticationActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;

import java.util.Calendar;
import java.util.Date;


public class AddRaceFragment extends Fragment {
    View view;
    private EditText et_race_name;
    private EditText et_race_start_date;
    private EditText et_race_end_date;
    private EditText et_race_start_time;
    private EditText et_race_end_time;
    private FloatingActionButton addRaceButton;
    private AddRaceViewModel addRaceViewModel;
    private ImageButton bt_start_date_picker;
    private ImageButton bt_end_date_picker;
    boolean isAllFieldsChecked = false;
    DatePickerDialog datePickerDialog;
    String[] data = {"Java", "Python", "C++", "C#", "Angular", "Go", "Python", "C++", "C#", "Angular", "Go"};
    ArrayAdapter adapter;
    Spinner spinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_addrace, container, false);
        datePickerDialog = new DatePickerDialog(getContext());
        bt_start_date_picker = view.findViewById(R.id.bt_pick_start_date);
        bt_end_date_picker = view.findViewById(R.id.bt_pick_end_date);


        et_race_name = view.findViewById(R.id.race_name);

        et_race_start_date = view.findViewById(R.id.race_start_date);
        et_race_end_date = view.findViewById(R.id.race_end_date);

        et_race_start_time = view.findViewById(R.id.race_start_time);
        et_race_end_time = view.findViewById(R.id.race_end_time);

        addRaceButton = view.findViewById(R.id.race_add_button);
        addRaceViewModel = AddRaceViewModel.getInstance();

        //May throw an error
        adapter = new ArrayAdapter<String>(view.getContext(), R.layout.spinner_item_selected, data);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner = view.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        addRaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //isAllFieldsChecked = CheckAllFields();

                if (isAllFieldsChecked) {
                    addRace();
//                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.list_of_races);
                }
            }
        });
        bt_start_date_picker.setOnClickListener(view -> {
            datePickerDialog.show();
            datePickerDialog.setOnDateSetListener((datePicker, year, month, dayOfMonth) -> {
                Calendar mCalendar = Calendar.getInstance();
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
                et_race_start_date.setText(selectedDate);
            });
        });

        bt_end_date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
                datePickerDialog.setOnDateSetListener((datePicker, year, month, dayOfMonth) -> {
                    Calendar mCalendar = Calendar.getInstance();
                    mCalendar.set(Calendar.YEAR, year);
                    mCalendar.set(Calendar.MONTH, month);
                    mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
                    et_race_end_date.setText(selectedDate);
                });
            }
        });
        return view;
    }

    public void addRace() {


        Race raceToAdd = new Race();
        addRaceViewModel.addRace(raceToAdd);
    }


    private void CheckAllFields() {
        if (TextUtils.isEmpty(et_race_name.getText())) {
            et_race_name.setError("Race name field is required");
            return;
        }
        if (TextUtils.isEmpty(et_race_start_date.getText())) {
            et_race_start_date.setError("The race should have start date");
            return;
        }
        if (TextUtils.isEmpty(et_race_end_date.getText())) {
            et_race_end_date.setError("The race should have start date");
            return;
        }
        if (TextUtils.isEmpty(et_race_start_date.getText())) {
            et_race_start_date.setError("The race should have start date");
            return;
        }
        if (TextUtils.isEmpty(et_race_end_date.getText())) {
            et_race_end_date.setError("The race should have start date");
            return;
        }


        if (et_race_start_time.length() == 0) {
            et_race_start_time.setError("Start time is required");
            return;
        } else {
            if (et_race_end_time.getInputType() != InputType.TYPE_CLASS_DATETIME) {
                et_race_end_date.setError("Start time input should be of time format");
                return;
            }
        }

//        if (endTime.length() == 0) {
//            endTime.setError("End time is required");
//            return;
//        } else {
//            if (endTime.getInputType() != InputType.TYPE_CLASS_DATETIME) {
//                endTime.setError("End time input should be of time format");
//                return;
//            }
//        }
//
//        if (raceDate.length() == 0) {
//            raceDate.setError("Race date is required");
//            return;
//        } else {
//            if (raceDate.getInputType() != InputType.TYPE_CLASS_DATETIME) {
//                raceDate.setError("This input type should be of date format");
//                return;
//            }
//        }
//        if (raceCategory.length() == 0) {
//            raceCategory.setError("Race category is required");
//            return;
//        }
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//
//        Date dateToAdd = null;
//        try {
//            dateToAdd = dateFormat.parse(String.valueOf(raceDate));
//        } catch (ParseException e) {
//            raceDate.setError("Invalid format,try dd/MM/yyyy");
//            return;
//        }
    }


}