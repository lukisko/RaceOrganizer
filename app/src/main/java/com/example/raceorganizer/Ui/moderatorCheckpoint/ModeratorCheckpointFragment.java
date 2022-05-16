package com.example.raceorganizer.Ui.moderatorCheckpoint;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raceorganizer.Adapters.CheckpointAdapter;
import com.example.raceorganizer.Adapters.ParticipantAdapter;
import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.MainActivity;
import com.example.raceorganizer.R;
import com.example.raceorganizer.Ui.Home.HomeFragment;
import com.example.raceorganizer.Ui.checkpointRaceParticipants.CheckpointRaceParticipantsViewModel;

import java.util.ArrayList;

public class ModeratorCheckpointFragment extends Fragment {
    private RecyclerView checkpointRecycler;
    private CheckpointAdapter checkpointAdapter;
    private View view;
    private ModeratorCheckpointViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_moderator_checkpoint, container, false);
        viewModel= new ViewModelProvider(this).get(ModeratorCheckpointViewModel.class);
        checkpointRecycler = view.findViewById(R.id.checkpointModeratorList);

        checkpointRecycler.hasFixedSize();
        checkpointRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));

        checkpointAdapter = new CheckpointAdapter(new ArrayList<>());

        viewModel.getCheckpoints(getArguments().getString("idOfRace")).observe(getViewLifecycleOwner(), x -> {
            checkpointAdapter.set(x);
        });

        checkpointAdapter = new CheckpointAdapter(new ArrayList<>());

        setupRecycleViewsOnClick();
        checkpointRecycler.setAdapter(checkpointAdapter);
        return view;
    }

    public void setupRecycleViewsOnClick() {
        Bundle bundle = new Bundle();
        checkpointAdapter.setOnClickListener(c -> {
            bundle.putString("idOfCheckpoint", c.getId());
                ((MainActivity) this.getActivity()).navController.navigate(R.id.checkpointRaceParticipants, bundle);
        });
    }
}
