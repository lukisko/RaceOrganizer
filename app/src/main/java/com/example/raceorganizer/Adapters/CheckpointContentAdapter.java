package com.example.raceorganizer.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raceorganizer.R;
import com.example.raceorganizer.Data.Model.Checkpoint;

import java.util.ArrayList;

public class CheckpointContentAdapter extends RecyclerView.Adapter<CheckpointContentAdapter.ViewHolder>{
    private ArrayList<Checkpoint> checkpoints;
    public CheckpointContentAdapter(ArrayList<Checkpoint> checkpoints){
        //TODO change this class so it will work
        this.checkpoints = checkpoints;
    }
    @NonNull
    @Override
    public CheckpointContentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_checkpointlist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckpointContentAdapter.ViewHolder holder, int position) {
        holder.name.setText(checkpoints.get(position).getName());
        holder.info.setText(checkpoints.get(position).getInfo());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView info;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.checkpointName);
            info = itemView.findViewById(R.id.checkpointInfo);
        }
    }

    public interface OnClickListener {
        void onClick(Checkpoint checkpoint);
    }
}
