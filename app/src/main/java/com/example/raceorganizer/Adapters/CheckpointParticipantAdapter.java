package com.example.raceorganizer.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Participant;
import com.example.raceorganizer.R;

import java.util.ArrayList;

public class CheckpointParticipantAdapter extends RecyclerView.Adapter<CheckpointParticipantAdapter.ViewHolder> {

    private ArrayList<Participant> participants;
    private CheckpointParticipantAdapter.OnCLickListener onCLickListener;

    public CheckpointParticipantAdapter(ArrayList<Participant> participants) {
        this.participants = participants;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void set(ArrayList<Participant> participants) {
        this.participants = participants;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CheckpointParticipantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.participant_list, parent, false);
        return new CheckpointParticipantAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckpointParticipantAdapter.ViewHolder holder, int position) {
        holder.participantNumber.setText(participants.get(position).getNumber());
        holder.firstName.setText(participants.get(position).getFirstName());
        holder.lastName.setText(participants.get(position).getLastName());
    }

    @Override
    public int getItemCount() {
        return participants.size();
    }

    public void setOnClickListener(CheckpointParticipantAdapter.OnCLickListener listener) {
        this.onCLickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView participantNumber;
        private TextView firstName;
        private TextView lastName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            participantNumber = itemView.findViewById(R.id.numberOfParticipant);
            firstName = itemView.findViewById(R.id.firstNameOfParticipant);
            lastName = itemView.findViewById(R.id.lastNameOfParticipant);

//            itemView.setOnClickListener(l -> {
//                onCLickListener.onClick(checkpoints.get(getBindingAdapterPosition()));
//            });
        }
    }

    public interface OnCLickListener {
        void onClick(Checkpoint checkpoint);
    }
}
