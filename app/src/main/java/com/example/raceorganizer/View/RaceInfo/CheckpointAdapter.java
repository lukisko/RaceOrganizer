package com.example.raceorganizer.View.RaceInfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.R;

import java.util.ArrayList;

public class CheckpointAdapter extends RecyclerView.Adapter<CheckpointAdapter.ViewHolder> {

    private ArrayList<Checkpoint> checkpoints;
    private OnCLick listener;

    public CheckpointAdapter(ArrayList<Checkpoint> checkpoints){
        this.checkpoints = checkpoints;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.checkpoint_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(checkpoints.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return checkpoints.size();
    }

    public void setOnClickListener(OnCLick listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private Button name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            itemView.setOnClickListener(l -> {
                listener.onClick(checkpoints.get(getBindingAdapterPosition()));
            });
        }
    }
    public interface OnCLick{
        void onClick(Checkpoint checkpoint);
    }
}
