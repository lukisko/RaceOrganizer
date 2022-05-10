package com.example.raceorganizer.ui.barCodeScan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.raceorganizer.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


import java.util.ArrayList;
import java.util.Objects;

public class BarCodeFragment extends Fragment implements View.OnClickListener {
    private Button scanBtn;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_barcode_scaner, container, false);

        scanBtn = view.findViewById(R.id.button_capture);

        scanBtn.setOnClickListener(this);

        // Create an instance of Camera
        //mCamera = getCameraInstance();
        //mPreview = new CameraPreview(this.getContext(),mCamera);
        //FrameLayout frame = view.findViewById(R.id.camera_preview);
        //frame.addView(mPreview);

        return view;
    }

    @Override
    public void onClick(View v) {
        // we need to create the object
        // of IntentIntegrator class
        // which is the class of QR library
        IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity());
        intentIntegrator.setPrompt("Scan a barcode or QR Code");
        intentIntegrator.setOrientationLocked(true);
        ArrayList<String> barCodeTypes = new ArrayList<>();
        barCodeTypes.add("QR_CODE");
        intentIntegrator.initiateScan(barCodeTypes);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
