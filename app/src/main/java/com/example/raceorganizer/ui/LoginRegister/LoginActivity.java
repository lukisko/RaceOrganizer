package com.example.raceorganizer.ui.LoginRegister;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.raceorganizer.R;
import com.example.raceorganizer.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private View view;
    private FloatingActionButton next;
    private FirebaseAuth firebaseAuth;
    private EditText et_username, et_password;
    private Button bt_login, bt_register;
    private ProgressBar progressbar;
    private Intent mainActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();

        mainActivity = new Intent(LoginActivity.this, MainActivity.class);

        et_username = findViewById(R.id.et_logIn_username);
        et_password = findViewById(R.id.et_logIn_password);

        bt_login = findViewById(R.id.bt_logIn_login);
        bt_register = findViewById(R.id.bt_logIn_register_navigation);

        progressbar = findViewById(R.id.pg_login);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signIn();
            }
        });
    }

    @Override
    protected void onStart() {//This method should be implemented on the main activity as well
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser.getUid() != null) {

        }

        //firebaseAuth.signInWithEmailAndPassword();
        //send to the main activity if the user is logged in
        //updateUI(currentUser);
    }


    private void signIn() {

        progressbar.setVisibility(View.VISIBLE);

        String username, password;
        username = et_username.getText().toString();
        password = et_password.getText().toString();

        if (TextUtils.isEmpty(username) && !username.contains("@")) {
            et_username.setError("Please enter your user email email!");
        }
        if (TextUtils.isEmpty(password)) {
            et_password.setError("Please enter password!");
        } else if (et_password.getText().toString().length() < 6) {
            et_password.setError("Password should contain at least 6 characters!");
        }

        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressbar.setVisibility(View.GONE);
                    startActivity(mainActivity);
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Login failed!!",
                            Toast.LENGTH_LONG)
                            .show();
                    progressbar.setVisibility(View.GONE);
                }
            }
        });

    }
}
