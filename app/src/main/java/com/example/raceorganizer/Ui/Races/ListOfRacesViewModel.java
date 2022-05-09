package com.example.raceorganizer.Ui.Races;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.LiveData.RacesLiveData;
import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Repository.RaceRepository;

import java.util.ArrayList;

public class ListOfRacesViewModel extends ViewModel {

    private RaceRepository raceRepository;

    public ListOfRacesViewModel(){
        raceRepository = RaceRepository.getInstance();
    }
    public void init(){
        raceRepository.init(null);
    }
    public RacesLiveData getAllRaces(){
        return raceRepository.getAllRaces();
    }
}
