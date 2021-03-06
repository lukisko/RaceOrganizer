package com.example.raceorganizer.Data.LiveData.Participant;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Participant;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.ArrayList;

public class ParticipantLiveData extends LiveData<Participant> {
    DocumentReference reference;
    private ListenerRegistration listenerRegistration;


    private final EventListener<DocumentSnapshot> valueEventListener = new EventListener<DocumentSnapshot>() {
        @Override
        public void onEvent(@Nullable DocumentSnapshot document, @Nullable FirebaseFirestoreException error) {
            Participant participant = new Participant(
                    document.getString("Id"),
                    document.getString("FirstName"),
                    document.getString("LastName"),
                    Integer.parseInt(document.get("Age").toString()),
                    Integer.parseInt(document.get("Number").toString()),
                    Integer.parseInt(document.get("Points").toString()),
                    (Timestamp)document.get("TotalTime")
            );
            participant.setRaceIds((ArrayList<String>) document.get("Races"));
            setValue(participant);
        }

    };


    public ParticipantLiveData(DocumentReference ref){
        reference = ref;
    }

    public ParticipantLiveData(){

    }

    @Override
    protected void onActive() {
        super.onActive();
        if(reference != null)
        listenerRegistration = reference.addSnapshotListener(valueEventListener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if(reference != null)
        listenerRegistration.remove();
    }
}
