package com.example.raceorganizer.Ui.login_activity;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
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
import androidx.lifecycle.ViewModelProvider;

import com.example.raceorganizer.Data.Model.User;
import com.example.raceorganizer.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {
    View view;
    private FloatingActionButton next;
    private FirebaseAuth firebaseAuth;
    private EditText et_username, et_password;
    private Button bt_login, bt_register;
    private ProgressBar progressbar;
    private LoginViewModel viewModel;
    private Intent intent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        intent = new Intent();
        firebaseAuth = FirebaseAuth.getInstance();


        et_username = view.findViewById(R.id.et_logIn_username);
        et_password = view.findViewById(R.id.et_logIn_password);

        bt_login = view.findViewById(R.id.bt_logIn_login);
        bt_register = view.findViewById(R.id.bt_logIn_register_navigation);

        progressbar = view.findViewById(R.id.pg_login);
        progressbar.setVisibility(View.INVISIBLE);
        bt_login.setOnClickListener(view -> signIn());

        bt_register.setOnClickListener(view -> {
            ((AuthenticationActivity) getActivity()).navController.navigate(R.id.nav_register);
        });

        return view;
    }


    private void signIn() {

        String username, password;
        username = et_username.getText().toString();
        password = et_password.getText().toString();

        if (TextUtils.isEmpty(username) && !username.contains("@")) {
            et_username.setError("Please enter your user email email!");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            et_password.setError("Please enter password!");
            return;
        } else if (et_password.getText().toString().length() < 6) {
            et_password.setError("Password should contain at least 6 characters!");
            return;
        }
        progressbar.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {

                intent.putExtra("user", viewModel.getCurrentUser().getValue());
                Toast.makeText(getContext(),
                        "Logged In",
                        Toast.LENGTH_LONG)
                        .show();
                getActivity().setResult(RESULT_OK, intent);
                getActivity().finish();
            } else {

                Toast.makeText(getContext(),
                        "Login failed!!",
                        Toast.LENGTH_LONG)
                        .show();
                progressbar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
