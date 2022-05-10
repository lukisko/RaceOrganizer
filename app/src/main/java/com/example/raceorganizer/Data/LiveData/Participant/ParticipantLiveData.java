package com.example.raceorganizer.Data.LiveData.Participant;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.Model.Checkpoint;
import com.example.raceorganizer.Data.Model.Participant;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

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
                    Integer.parseInt(document.getString("Age")),
                    Integer.parseInt(document.getString("Number")),
                    Integer.parseInt(document.getString("Points")),
                    Integer.parseInt(document.getString("TotalTime"))
            );
            setValue(participant);
        }

    };


    public ParticipantLiveData(DocumentReference ref){
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
