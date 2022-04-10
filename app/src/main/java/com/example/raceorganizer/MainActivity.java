package com.example.raceorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();


        Button organize = findViewById(R.id.organize);
        organize.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                navController.navigate(R.id.login_register2);

            }
        });

    }

    private void setup(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }


}