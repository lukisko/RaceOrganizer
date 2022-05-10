package com.example.raceorganizer.ui.register_fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.raceorganizer.MainActivity;
import com.example.raceorganizer.R;
import com.example.raceorganizer.ui.login_activity.AuthenticationActivity;
import com.example.raceorganizer.ui.login_activity.LoginViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore dbFirebase;

    private View view;

    private EditText et_firstName;
    private EditText et_lastName;
    private EditText et_username;
    private EditText et_password;

    private Button bt_logInButton;
    private Button bt_registerButton;

    private ProgressBar progressbar;
    private Snackbar snackbar;
    private RegisterViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        firebaseAuth = FirebaseAuth.getInstance();
        view = inflater.inflate(R.layout.fragment_register, container, false);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        et_firstName = view.findViewById(R.id.et_register_firstname);
        et_lastName = view.findViewById(R.id.et_register_lastName);
        et_username = view.findViewById(R.id.et_register_username);
        et_password = view.findViewById(R.id.et_register_password);
        dbFirebase = FirebaseFirestore.getInstance();
        bt_logInButton = view.findViewById(R.id.bt_register_login);
        bt_registerButton = view.findViewById(R.id.bt_register_register);
        progressbar = view.findViewById(R.id.pb_register);
        progressbar.setVisibility(View.INVISIBLE);
        bt_registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
        bt_logInButton.setOnClickListener(v -> ((AuthenticationActivity) getActivity()).navController.navigate(R.id.nav_login));

        return view;
    }

    private void registerNewUser() {

        String firstName, lastName, username, password;
        firstName = et_firstName.getText().toString();
        lastName = et_lastName.getText().toString();
        username = et_username.getText().toString();
        password = et_password.getText().toString();

        progressbar.setVisibility(View.VISIBLE);

        if (TextUtils.isEmpty(firstName)) {
            et_firstName.setError("Please enter first name!");
            return;
        }
        if (TextUtils.isEmpty(lastName)) {
            et_lastName.setError("Please enter last name!");
            return;
        }
        if (TextUtils.isEmpty(username)) {
            et_username.setError("Please enter user name!");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            et_password.setError("Please enter a password!");
            return;
        } else if (et_password.getText().toString().length() < 6) {
            et_password.setError("Password should contain at least 6 characters!");
            return;
        }

        Map<String, String> newUser = new HashMap<String, String>();
        newUser.put("FirstName", firstName);
        newUser.put("LastName", lastName);


        firebaseAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                dbFirebase.collection("LoggedInUser").document(task.getResult().getUser().getUid())
                        .set(newUser)
                        .addOnSuccessListener(unused -> {
                            progressbar.setVisibility(View.INVISIBLE);
                            Toast.makeText(view.getContext(), "Logged In", Toast.LENGTH_LONG).show();
                        }).addOnFailureListener(e -> Toast.makeText(view.getContext(), "Something went wrong in the Firestore DB ", Toast.LENGTH_LONG).show());

            } else {
                et_firstName.setText("");
                et_lastName.setText("");
                et_firstName.setText("");
                et_lastName.setText("");
                Toast.makeText(view.getContext(), "Something is wrong in the Authentication DB", Toast.LENGTH_LONG).show();
            }
        });
    }


}


