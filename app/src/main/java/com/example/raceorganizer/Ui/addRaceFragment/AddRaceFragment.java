package com.example.raceorganizer.Ui.addRaceFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Data.Model.RaceType;
import com.example.raceorganizer.MainActivity;
import com.example.raceorganizer.R;
import com.example.raceorganizer.Ui.login_activity.AuthenticationActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;


public class AddRaceFragment extends Fragment {
    View view;
    private EditText et_race_name;
    private EditText et_race_start_date;
    private EditText et_race_end_date;
    private EditText et_race_start_time;
    private EditText et_race_end_time;
    private EditText et_race_category;

    private FloatingActionButton addRaceButton;
    private AddRaceViewModel addRaceViewModel;
    private ImageButton bt_start_date_picker;
    private ImageButton bt_end_date_picker;
    private ImageButton bt_start_time_picker;
    private ImageButton bt_end_time_picker;
    boolean isAllFieldsChecked = false;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;


    private ArrayList<RaceType> arrayList;
    private Dialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_addrace, container, false);
        datePickerDialog = new DatePickerDialog(getContext());

        bt_start_date_picker = view.findViewById(R.id.bt_pick_start_date);
        bt_end_date_picker = view.findViewById(R.id.bt_pick_end_date);

        bt_start_time_picker = view.findViewById(R.id.bt_pick_start_time);
        bt_end_time_picker = view.findViewById(R.id.bt_pick_end_time);

        et_race_name = view.findViewById(R.id.race_name);

        et_race_start_date = view.findViewById(R.id.race_start_date);
        et_race_end_date = view.findViewById(R.id.race_end_date);

        et_race_start_time = view.findViewById(R.id.race_start_time);
        et_race_end_time = view.findViewById(R.id.race_end_time);

        et_race_category = view.findViewById(R.id.et_race_category);

        addRaceButton = view.findViewById(R.id.race_add_button);
        addRaceViewModel = AddRaceViewModel.getInstance(getActivity().getApplication());


        arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList(RaceType.values()));
        et_race_category.setOnClickListener(v -> {
            dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.spinner_view);
            dialog.getWindow().setLayout(900, 1000);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            ListView listView = dialog.findViewById(R.id.list_view);
            ArrayAdapter<RaceType> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_dropdown_item, arrayList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                et_race_category.setText(adapter.getItem(position).toString());
                dialog.dismiss();
            });
        });


        addRaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRace();
                ((MainActivity)getActivity()).navController.navigate(R.id.list_of_races);

            }
        });

        bt_start_date_picker.setOnClickListener(view -> {
            datePickerDialog.show();
            datePickerDialog.setOnDateSetListener((datePicker, year, month, dayOfMonth) -> {
                Calendar mCalendar = Calendar.getInstance();
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String selectedDate = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(mCalendar.getTime());
                et_race_start_date.setText(selectedDate);
            });
        });

        bt_end_date_picker.setOnClickListener(view -> {
            datePickerDialog.show();
            datePickerDialog.setOnDateSetListener((datePicker, year, month, dayOfMonth) -> {
                Calendar mCalendar = Calendar.getInstance();
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String selectedDate = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(mCalendar.getTime());
                et_race_end_date.setText(selectedDate);
            });
        });
        bt_start_time_picker.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            timePickerDialog = new TimePickerDialog(getContext(), (view1, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                SimpleDateFormat time_format = new SimpleDateFormat("HH:mm");
                String timeComp = time_format.format(calendar.getTime());
                et_race_start_time.setText(timeComp);
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
            timePickerDialog.show();

        });
        bt_end_time_picker.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            timePickerDialog = new TimePickerDialog(getContext(), (view1, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                SimpleDateFormat time_format = new SimpleDateFormat("HH:mm");
                String timeComp = time_format.format(calendar.getTime());
                et_race_end_time.setText(timeComp);
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
            timePickerDialog.show();
        });

        return view;
    }

    public void addRace() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy:HH:mm");
        if (CheckAllFields()) {
            try {
                Race raceToAdd = new Race();
                raceToAdd.setName(et_race_name.getText().toString());

                Date startDate = formatter.parse(et_race_start_date.getText().toString() + ":" + et_race_start_time.getText().toString());
                raceToAdd.setStart(startDate);

                Date endDate = formatter.parse(et_race_start_date.getText().toString() + ":" + et_race_end_time.getText().toString());
                raceToAdd.setEnd(endDate);

                raceToAdd.setRaceType(et_race_category.getText().toString());
                raceToAdd.setRaceOwner(addRaceViewModel.getCurrentUser().getValue().getUid());
                addRaceViewModel.addRace(raceToAdd);
                Toast.makeText(view.getContext(), "Race added successfully",
                        Toast.LENGTH_LONG).show();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean CheckAllFields() {
        if (TextUtils.isEmpty(et_race_name.getText())) {
            et_race_name.setError("Race name field is required");
            return false;
        }
        if (TextUtils.isEmpty(et_race_start_date.getText())) {
            et_race_start_date.setError("The race should have start date");
            return false;
        }
        if (TextUtils.isEmpty(et_race_end_date.getText())) {
            et_race_end_date.setError("The race should have end date");
            return false;
        }

//        if (Integer.parseInt(et_race_start_date.getText().toString()) > Integer.parseInt(et_race_start_date.getText().toString())) {
//            et_race_start_date.setError("The race cannot start after the end date");
//            return false;
//        }
        if (TextUtils.isEmpty(et_race_start_time.getText())) {
            et_race_start_date.setError("The race should have start time");
            return false;
        }
        if (TextUtils.isEmpty(et_race_end_time.getText())) {
            et_race_end_date.setError("The race should have end date");
            return false;
        }
        if (TextUtils.isEmpty(et_race_category.getText())) {
            et_race_end_date.setError("The race should have a category");
            return false;
        }
        return true;
    }


}