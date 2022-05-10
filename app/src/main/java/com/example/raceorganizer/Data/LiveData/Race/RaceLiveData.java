package com.example.raceorganizer.Data.LiveData.Race;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.Model.Race;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

public class RaceLiveData extends LiveData<Race> {
    DocumentReference reference;
    private ListenerRegistration listenerRegistration;
    private Race result;

    private final EventListener<DocumentSnapshot> valueEventListener = new EventListener<DocumentSnapshot>() {
        @Override
        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
            result.setId(value.getId());
            result.setName(value.getString("Name"));
            result.setStart(value.getDate("Start"));
            result.setEnd(value.getDate("End"));
            setValue(RaceLiveData.this.result);
        }

    };


    public RaceLiveData(DocumentReference ref){
        result = new Race();
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
