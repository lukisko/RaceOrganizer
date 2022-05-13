package com.example.raceorganizer.Data.Dao;

import com.example.raceorganizer.Data.LiveData.Moderator.ModeratorLiveData;
import com.example.raceorganizer.Data.LiveData.Moderator.ModeratorsLiveData;
import com.example.raceorganizer.Data.LiveData.Participant.ParticipantLiveData;
import com.example.raceorganizer.Data.Model.RegisteredUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Map;

public class ModeratorDao {
    private static ModeratorDao instance;
    private FirebaseFirestore databaseRef;


    private ModeratorDao(){
        databaseRef = FirebaseFirestore.getInstance();
    }

    public static synchronized ModeratorDao getInstance(){
        if(instance == null){
            return new ModeratorDao();
        }
        return instance;
    }

    public ModeratorsLiveData getModerators(ArrayList<String> moderatorsIds){
        Query query = databaseRef.collection("LoggedInUser").whereIn(FieldPath.documentId(),moderatorsIds);
        ModeratorsLiveData moderatorsLiveData = new ModeratorsLiveData(query);
        return moderatorsLiveData;
    }

    public ModeratorLiveData getModerator(String id) {
        DocumentReference reference = databaseRef.collection("LoggedInUser").document(id);
        ModeratorLiveData moderatorLiveData = new ModeratorLiveData(reference);
        return moderatorLiveData;
    }

}
