package com.example.raceorganizer.ui.checkpointList;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raceorganizer.R;

public class CheckpointListPFragment extends AppCompatActivity {
    private CheckpointListPViewModel checkpointViewModel;

    RecyclerView checkpointRecycler;
    CheckpointAdapter checkpointAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        checkpointViewModel = new ViewModelProvider(this).get(CheckpointListPViewModel.class);
        checkpointRecycler = findViewById(R.id.checkpointList);

        checkpointAdapter = new CheckpointAdapter(checkpointViewModel.getCheckpoints().getValue());
        checkpointRecycler.setAdapter(checkpointAdapter);

    }
}
