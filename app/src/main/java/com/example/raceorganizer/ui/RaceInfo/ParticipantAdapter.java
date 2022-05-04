package com.example.raceorganizer.ui.RaceInfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raceorganizer.Data.Model.Participant;
import com.example.raceorganizer.R;

import java.util.ArrayList;

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ViewHolder> {

    private ArrayList<Participant> participants;
    private OnCLickListener onCLickListener;

    public ParticipantAdapter(ArrayList<Participant> participants){
        this.participants = participants;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.participant_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.number.setText(participants.get(position).getNumber());
        holder.firstName.setText(participants.get(position).getFirstName());
        holder.lastName.setText(participants.get(position).getLastName());
    }

    @Override
    public int getItemCount() {
        return participants.size();
    }

    public void setOnClickListener(OnCLickListener listener) {
        this.onCLickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView number;
        private TextView firstName;
        private TextView lastName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.numberOfParticipant);
            firstName = itemView.findViewById(R.id.firstNameOfParticipant);
            lastName = itemView.findViewById(R.id.lastNameOfParticipant);
//            itemView.setOnClickListener(l -> {
//                onCLickListener.onClick(participants.get(getBindingAdapterPosition()));
//            });
        }
    }
    public interface OnCLickListener{
        void onClick(Participant participant);
    }
}
