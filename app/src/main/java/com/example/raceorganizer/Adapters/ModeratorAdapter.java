package com.example.raceorganizer.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raceorganizer.Data.Model.Participant;
import com.example.raceorganizer.Data.Model.RegisteredUser;
import com.example.raceorganizer.Data.Model.User;
import com.example.raceorganizer.R;

import java.util.ArrayList;

public class ModeratorAdapter extends RecyclerView.Adapter<ModeratorAdapter.ViewHolder> {

    private ArrayList<RegisteredUser> moderators;
    private OnCLickListener onCLickListener;

    public ModeratorAdapter(ArrayList<RegisteredUser> moderators){
        this.moderators = moderators;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void set(ArrayList<RegisteredUser> participants)
    {
        this.moderators = participants;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.moderator_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.firstName.setText(moderators.get(position).getFirstName());
        holder.lastName.setText(moderators.get(position).getLastName());
    }

    @Override
    public int getItemCount() {
        return moderators.size();
    }

    public void setOnClickListener(OnCLickListener listener) {
        this.onCLickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView firstName;
        private TextView lastName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.firstNameOfModerator);
            lastName = itemView.findViewById(R.id.lastNameOfModerator);
//            itemView.setOnClickListener(l -> {
//                onCLickListener.onClick(moderators.get(getBindingAdapterPosition()));
//            });
        }
    }
    public interface OnCLickListener{
        void onClick(RegisteredUser participant);
    }
}