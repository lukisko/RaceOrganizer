package com.example.raceorganizer.Ui.login_activity;

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




}
