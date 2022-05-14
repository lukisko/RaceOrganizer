package com.example.raceorganizer.Ui.checkpointRaceParticipants;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raceorganizer.Adapters.CheckpointAdapter;
import com.example.raceorganizer.Adapters.ParticipantAdapter;
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
    private RaceLiveData raceLive;
    private CheckpointLiveData checkpointLive;

    private TextView checkpointName;
    private TextView raceStartTime;
    private TextView raceEndTime;
    private EditText et_searchParticipant;
    private ImageButton searchButton;

    private RecyclerView participantRecycler;
    private ParticipantAdapter participantAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_checkpointlist_participant, container, false);
        viewModel = new ViewModelProvider(this).get(CheckpointRaceParticipantsViewModel.class);

        //Log.i("Id of  from previous frag", getArguments().getString("idOfCheckpoint"));
        // This call trows an error
        checkpointLive = viewModel.getCheckpoint(getArguments().getString("idOfCheckpoint"));
        //Log.i("Name of checkpoint from db", checkpointLive.getValue().toString());
        //Log.i("Id of race from db", s.getValue().getRaceId());

        raceLive = viewModel.getRace(checkpointLive.getValue().getRaceId());
        participantAdapter = new ParticipantAdapter(new ArrayList<>());

        participantRecycler = view.findViewById(R.id.participantList);

        checkpointName = view.findViewById(R.id.et_checkpoint_name_label);
        raceStartTime = view.findViewById(R.id.start_time_label);
        raceEndTime = view.findViewById(R.id.end_time_label);
        et_searchParticipant = view.findViewById(R.id.et_search_participant);
        searchButton = view.findViewById(R.id.bt_search_button);

        raceLive.observe(getViewLifecycleOwner(), (Race race) -> {
            raceStartTime.setText(race.getStart());
            raceEndTime.setText(race.getEnd());
        });
        checkpointLive.observe(getViewLifecycleOwner(), (Checkpoint checkpoint) ->
        {
            checkpointName.setText(checkpoint.getName());
        });
        participantRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        viewModel.getParticipants(checkpointLive.getValue().getRaceId()).
                observe(getViewLifecycleOwner(), participants ->
                {
                    participantAdapter.set(participants);
                });
        participantRecycler.setAdapter(participantAdapter);

        participantAdapter.setOnClickListener(o -> {
            Bundle bundle = new Bundle();
            bundle.putString("idOfParticipant", o.getId());
            ((MainActivity) this.getActivity()).navController.navigate(R.id.checkpointListPFragment, bundle);
        });

        return view;

    }
}
