package com.example.raceorganizer.ui.LoginRegister;

import android.os.Bundle;
import android.text.TextUtils;
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

import com.example.raceorganizer.Data.Model.LoggedInUser;
import com.example.raceorganizer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        firebaseAuth = FirebaseAuth.getInstance();
        view = inflater.inflate(R.layout.activity_login, container, false);

        et_firstName = view.findViewById(R.id.et_register_firstname);
        et_lastName = view.findViewById(R.id.et_register_lastName);
        et_username = view.findViewById(R.id.et_register_username);
        et_password = view.findViewById(R.id.et_register_password);

        bt_logInButton = view.findViewById(R.id.bt_register_login);
        bt_registerButton = view.findViewById(R.id.bt_register_register);

        progressbar = view.findViewById(R.id.pb_register);

        bt_registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });

        bt_logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //go back
            }
        });

        return view;
    }

    private void registerNewUser() {

        String firstName, lastName, username, password;
        firstName = et_firstName.getText().toString();
        lastName = et_lastName.getText().toString();
        username = et_username.getText().toString();
        password = et_password.getText().toString();


        if (TextUtils.isEmpty(firstName)) {
            et_firstName.setError("Please enter first name!");
        }
        if (TextUtils.isEmpty(lastName)) {
            et_lastName.setError("Please enter last name!");
        }
        if (TextUtils.isEmpty(username)) {
            et_firstName.setError("Please enter user name!");
        }
        if (TextUtils.isEmpty(password)) {
            et_password.setError("Please enter a password!");
        } else if (et_password.getText().toString().length() < 6) {
            et_password.setError("Password should contain at least 6 characters!");
        }

        Map<String, String> newUser = new HashMap<String, String>();
        newUser.put("FirstName", firstName);
        newUser.put("LastName", firstName);

        firebaseAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    dbFirebase.collection("LoggedInUsers").document(firebaseAuth.getCurrentUser().getUid())
                            .set(newUser)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(view.getContext(), "User succesfully written in both dbs ", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view.getContext(), "Something went wrong in the Firestore DB ", Toast.LENGTH_LONG).show();
                        }
                    });

                } else {
                    et_firstName.setText("");
                    et_lastName.setText("");
                    et_firstName.setText("");
                    et_lastName.setText("");
                    Toast.makeText(view.getContext(), "Something is wrong in the Authentication DB", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}


