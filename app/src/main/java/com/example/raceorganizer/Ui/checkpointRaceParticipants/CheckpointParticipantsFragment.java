package com.example.raceorganizer.Ui.checkpointRaceParticipants;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raceorganizer.Adapters.CheckpointAdapter;
import com.example.raceorganizer.Adapters.ParticipantAdapter;
import com.example.raceorganizer.Adapters.RaceAdapter;
import com.example.raceorganizer.Data.LiveData.Checkpoint.CheckpointLiveData;
import com.example.raceorganizer.Data.LiveData.Race.RaceLiveData;
import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Participant;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.MainActivity;
import com.example.raceorganizer.R;
import com.example.raceorganizer.Ui.Home.HomeFragment;

import java.util.ArrayList;


public class CheckpointParticipantsFragment extends Fragment {
    private View view;
    private CheckpointRaceParticipantsViewModel viewModel;
    private TextView checkpointName;
    private TextView raceStartTime;
    private TextView raceEndTime;
    private EditText et_searchParticipant;
    private ImageButton searchButton;
    private String checkpointId;
    private RecyclerView participantRecycler;
    private ParticipantAdapter participantAdapter;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_checkpoint_race_participants, container, false);

        viewModel = new ViewModelProvider(this).get(CheckpointRaceParticipantsViewModel.class);

        sharedPreferences = getContext().getSharedPreferences("UserPref", MODE_PRIVATE);

        participantRecycler = view.findViewById(R.id.checkpoint_participant_list);

        checkpointName = view.findViewById(R.id.et_checkpoint_name_label);
        raceStartTime = view.findViewById(R.id.start_time_label);
        raceEndTime = view.findViewById(R.id.end_time_label);
        et_searchParticipant = view.findViewById(R.id.et_search_participant);
        searchButton = view.findViewById(R.id.bt_search_button);

        participantRecycler.hasFixedSize();
        participantRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));

        participantAdapter = new ParticipantAdapter(new ArrayList<>());

        viewModel.getCheckpoint(getArguments().getString("idOfCheckpoint")).observe(getViewLifecycleOwner(),
                (Checkpoint c) ->
                {
                    checkpointName.setText(c.getName());
                    checkpointId = c.getId();
                    System.out.println("Total Checkpoint"+ c.getTotalPoints());
                    viewModel.getParticipants(c.getRaceId()).
                            observe(getViewLifecycleOwner(), participants ->
                            {
                                participantAdapter.set(participants);
                            });
                    viewModel.getRace(c.getRaceId()).observe(getViewLifecycleOwner(), (Race r) -> {

                        raceStartTime.setText(r.getStart());
                        raceEndTime.setText(r.getEnd());
                    });

                }
        );

        participantAdapter.setOnClickListener(participant -> {
            Bundle bundle = new Bundle();
            bundle.putString("idOfParticipant", participant.getId());
            Log.i("id of participant", participant.getId());
            bundle.putString("idOfCheckpoint", checkpointId);
            ((MainActivity) getActivity()).navController.navigate(R.id.assign_points_fragment, bundle);
        });
        participantRecycler.setAdapter(participantAdapter);
        return view;
    }

}
