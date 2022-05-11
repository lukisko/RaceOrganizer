package com.example.raceorganizer.Ui.register_fragment;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;
import com.example.raceorganizer.Repository.LoggedInUserRepository;
import com.google.firebase.auth.FirebaseUser;

public class RegisterViewModel extends AndroidViewModel {
    private final LoggedInUserRepository userRepository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        userRepository = LoggedInUserRepository.getInstance(application);
    }
    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }

}
