package com.example.raceorganizer.ui.Races;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.R;

import java.util.ArrayList;

public class RaceAdapter extends RecyclerView.Adapter<RaceAdapter.ViewHolder> {

    private ArrayList<Race> races;
    private OnCLickListener onCLickListener;

    public RaceAdapter(ArrayList<Race> races){
        this.races = races;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.race_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(races.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return races.size();
    }

    public void setOnClickListener(OnCLickListener listener) {
        this.onCLickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            itemView.setOnClickListener(v -> {
                onCLickListener.onClick(races.get(getBindingAdapterPosition()));
            });
        }
    }
    public interface OnCLickListener{
        void onClick(Race race);
    }
}
