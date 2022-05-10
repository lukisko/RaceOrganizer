package com.example.raceorganizer.Ui.register_fragment;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;
import com.example.raceorganizer.Repository.AuthenticationRepository;
import com.example.raceorganizer.Data.Model.User;
import com.google.firebase.auth.FirebaseUser;

public class RegisterViewModel extends AndroidViewModel {
    private final AuthenticationRepository userRepository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        userRepository = AuthenticationRepository.getInstance(application);
    }
    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }
    public LiveData<FirebaseUser> signUp(User user) {
        return userRepository.getCurrentUser();
    }

}
