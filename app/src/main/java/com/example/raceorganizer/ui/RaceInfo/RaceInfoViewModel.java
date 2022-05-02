package com.example.raceorganizer.ui.RaceInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.Model.Race;
import com.example.raceorganizer.Repository.RaceRepository;

public class RaceInfoViewModel extends ViewModel {
    private RaceRepository repository;

    public RaceInfoViewModel(){
        repository = RaceRepository.getInstance();
    }

    public LiveData<Race> getRace(String name){
        return repository.getRace(name);
    }
}
