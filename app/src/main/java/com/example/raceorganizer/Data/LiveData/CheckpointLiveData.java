package com.example.raceorganizer.Data.LiveData;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CheckpointLiveData extends LiveData<ArrayList<Checkpoint>> {
    CollectionReference databaseReference;

    public CheckpointLiveData(CollectionReference ref) {
        databaseReference = ref;
    }


    public void startListener(){
        databaseReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                ArrayList<Checkpoint> checkpoints = new ArrayList<>();
                if (value.size() > 0){
                    for (DocumentSnapshot snapshot:value.getDocuments()) {
                        checkpoints.add(new Checkpoint(snapshot.get("Name").toString(),
                                Integer.parseInt(snapshot.get("PointsRecieved").toString()),
                                Integer.parseInt(snapshot.get("TotalPoints").toString())));
                    }
                }
                setValue(checkpoints);
            }
        });
    }

}
