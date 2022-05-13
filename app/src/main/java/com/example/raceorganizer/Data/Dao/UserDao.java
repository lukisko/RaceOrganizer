package com.example.raceorganizer.Data.Dao;

import com.example.raceorganizer.Data.LiveData.User.UserIdLiveData;
import com.example.raceorganizer.Data.LiveData.User.UserNameLiveData;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class UserDao {
    private static UserDao instance;
    private FirebaseFirestore databaseRef;

    private UserDao(){
        databaseRef = FirebaseFirestore.getInstance();
    }

    public static synchronized UserDao getInstance(){
        if(instance == null){
            return new UserDao();
        }
        return instance;
    }

    public UserIdLiveData getUserById(String id){
        DocumentReference userRef = databaseRef.collection("LoggedInUser").document(id);
        UserIdLiveData userIdLiveData = new UserIdLiveData(userRef);
        return userIdLiveData;
    }

    public UserNameLiveData getUserByName(String firstName, String lastName){
        Query ref = databaseRef.collection("LoggedInUser").whereEqualTo("FirstName", firstName).whereEqualTo("LastName", lastName);
        UserNameLiveData userNameLiveData = new UserNameLiveData(ref);
        return  userNameLiveData;
    }
}
