package com.example.raceorganizer.ui.RaceInfo;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.R;
import com.example.raceorganizer.ui.Races.RaceAdapter;

import java.util.ArrayList;

public class CheckpointAdapter extends RecyclerView.Adapter<CheckpointAdapter.ViewHolder> {

    private ArrayList<Checkpoint> checkpoints;
    private OnCLickListener onCLickListener;

    public CheckpointAdapter(ArrayList<Checkpoint> checkpoints){
        this.checkpoints = checkpoints;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void set(ArrayList<Checkpoint> checkpoints)
    {
        this.checkpoints = checkpoints;
        notifyDataSetChanged();
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

    public void setOnClickListener(OnCLickListener listener) {
        this.onCLickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameOfCheckpoint);
//            itemView.setOnClickListener(l -> {
//                onCLickListener.onClick(checkpoints.get(getBindingAdapterPosition()));
//            });
        }
    }
    public interface OnCLickListener{
        void onClick(Checkpoint checkpoint);
    }
}
