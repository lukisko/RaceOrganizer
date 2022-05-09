package com.example.raceorganizer.Data.LiveData.Race;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Data.Model.RaceType;
import com.example.raceorganizer.MainActivity;
import com.example.raceorganizer.R;
import com.example.raceorganizer.Ui.LoginRegister.LoginRegister;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.okhttp.internal.DiskLruCache;

import java.util.ArrayList;

public class RacesLiveData extends LiveData<ArrayList<Race>> {

    Query reference;
    private ArrayList<Race> races = new ArrayList<>();
    private ListenerRegistration listenerRegistration;

    private final EventListener<QuerySnapshot> valueEventListener = new EventListener<QuerySnapshot>() {
        @Override
        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
            for (DocumentSnapshot document :value.getDocuments()) {
                Race race = new Race(
                        document.getId(),
                        document.getString("Name"),
                        document.getDate("Start"),
                        document.getDate("End"),
                        RaceType.Marathon,
                        document.getString("RaceOwner"));
                races.add(race);
            }
            setValue(races);
        }

    };


    public RacesLiveData(Query ref){
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
