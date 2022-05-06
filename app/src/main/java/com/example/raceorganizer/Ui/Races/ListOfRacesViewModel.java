package com.example.raceorganizer.Ui.Races;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Repository.RaceRepository;

import java.util.ArrayList;

public class ListOfRacesViewModel extends ViewModel {

    private RaceRepository repository;

    public ListOfRacesViewModel(){

    }
    public LiveData<ArrayList<Race>> getAllRaces(){
        return null;
    }
}
