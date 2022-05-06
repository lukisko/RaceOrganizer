package com.example.raceorganizer.Ui.addParticipant;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.raceorganizer.R;

public class AddParticipantView extends AppCompatActivity {
    private AddParticipantViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(AddParticipantViewModel.class);

        EditText firstName = findViewById(R.id.participantFirstName);
        EditText lastName = findViewById(R.id.participantLastName);
        EditText age = findViewById(R.id.participantAge);
        EditText number = findViewById(R.id.participantNumber);

        firstName.setText(viewModel.getFirstName());
        lastName.setText(viewModel.getLastName());
        age.setText(viewModel.getAge());
        number.setText(viewModel.getNumber());

        Button createButton = findViewById(R.id.addParticipant);
        createButton.setOnClickListener((v)->{
            viewModel.setAge(age.getText().toString());
            viewModel.setFirstName(firstName.getText().toString());
            viewModel.setLastName(lastName.getText().toString());
            viewModel.setNumber(number.getText().toString());

            viewModel.makeParticipant();
        });
    }
}
