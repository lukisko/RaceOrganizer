package com.example.raceorganizer.Data.LiveData.Race;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.Model.Race;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RaceLiveData extends LiveData<Race> {
    DocumentReference reference;
    private ListenerRegistration listenerRegistration;
    private Race result;
    SimpleDateFormat formatter;

    private final EventListener<DocumentSnapshot> valueEventListener = new EventListener<DocumentSnapshot>() {
        @Override
        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
            formatter = new SimpleDateFormat("dd/MM/yyyy:HH:mm");
            Date startDate = null;
            Date endDate = null;
            try {
                startDate = formatter.parse(value.getString("Start"));
                endDate = formatter.parse(value.getString("Start"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            result.setId(value.getId());
            result.setName(value.getString("Name"));
            result.setStart(startDate);
            result.setEnd(endDate);
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
