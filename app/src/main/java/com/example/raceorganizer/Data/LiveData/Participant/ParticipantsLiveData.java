package com.example.raceorganizer.Data.LiveData.Participant;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Participant;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ParticipantsLiveData extends LiveData<ArrayList<Participant>> {
    Query reference;
    private ListenerRegistration listenerRegistration;


    private final EventListener<QuerySnapshot> valueEventListener = new EventListener<QuerySnapshot>() {
        ArrayList<Participant> participants = new ArrayList<>();
        @Override
        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
            for (DocumentSnapshot document:value.getDocuments()) {
                Participant participant = new Participant(
                        document.getString("Id"),
                        document.getString("FirstName"),
                        document.getString("LastName"),
                        Integer.parseInt(document.get("Age").toString()),
                        Integer.parseInt(document.get("Number").toString()),
                        Integer.parseInt(document.get("Points").toString()),
                        (Timestamp)document.get("TotalTime")
                        );
                participants.add(participant);
            }
            setValue(participants);
        }
    };




    public ParticipantsLiveData(Query ref) {
        reference = ref;

    }

    @Override
    protected void onActive() {
        super.onActive();
        listenerRegistration = reference.addSnapshotListener(valueEventListener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        listenerRegistration.remove();
    }
}
