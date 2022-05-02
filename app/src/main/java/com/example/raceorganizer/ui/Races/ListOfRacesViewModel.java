package com.example.raceorganizer.ui.Races;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Repository.RaceRepository;

import java.util.ArrayList;

public class ListOfRacesViewModel extends ViewModel {

    private RaceRepository repository;

    public ListOfRacesViewModel(){
        repository = RaceRepository.getInstance();
    }
    public LiveData<ArrayList<Race>> getAllRaces(){
        return repository.getAllRaces();
    }
}
