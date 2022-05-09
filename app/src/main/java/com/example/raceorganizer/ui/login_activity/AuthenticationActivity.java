package com.example.raceorganizer.ui.login_activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.raceorganizer.Data.Model.User;
import com.example.raceorganizer.MainActivity;
import com.example.raceorganizer.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class AuthenticationActivity extends AppCompatActivity {

    private View view;
    private FloatingActionButton next;
    private FirebaseAuth firebaseAuth;
    private EditText et_username, et_password;
    private Button bt_login, bt_register;
    private ProgressBar progressbar;
    public NavController navController;

    private LoginViewModel viewModel;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupNavigation();
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        intent = new Intent();
        firebaseAuth = FirebaseAuth.getInstance();

        et_username = findViewById(R.id.et_logIn_username);
        et_password = findViewById(R.id.et_logIn_password);

        bt_login = findViewById(R.id.bt_logIn_login);
        bt_register = findViewById(R.id.bt_logIn_register_navigation);

        progressbar = findViewById(R.id.pg_login);

        NavController navController = Navigation.findNavController(this, R.id.nav_login_fragment);

        bt_login.setOnClickListener(view -> signIn());

        bt_register.setOnClickListener(view -> {
            navController.navigate(R.id.nav_register);
        });
    }

    private void setupNavigation() {
        navController = Navigation.findNavController(this, R.id.nav_login_fragment);
    }


    @Override
    protected void onStart() {//This method should be implemented on the main activity as well
        super.onStart();
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                intent.putExtra("user", user.getUid());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    private void signIn() {

        progressbar.setVisibility(View.VISIBLE);

        User newUser = validation();
        firebaseAuth.signInWithEmailAndPassword(newUser.getUsername(), newUser.getPassword()).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                progressbar.setVisibility(View.GONE);
                intent.putExtra("user", viewModel.getCurrentUser().getValue());
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Login failed!!",
                        Toast.LENGTH_LONG)
                        .show();
                progressbar.setVisibility(View.GONE);
            }
        });
    }

    private User validation() {
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
        return new User(username, password);
    }


}
