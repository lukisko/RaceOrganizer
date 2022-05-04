package com.example.raceorganizer.ui.addRaceFragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.raceorganizer.Data.Model.Race;

public class AddRaceViewModel extends ViewModel {


    private static AddRaceViewModel instance;
    private final MutableLiveData<Race> raceToAdd;
    private final Race localRaceItem;

    private AddRaceViewModel() {
        raceToAdd = new MutableLiveData<>();
        localRaceItem = new Race("");
    }


    public static synchronized AddRaceViewModel getInstance() {
        if (instance == null)
            instance = new AddRaceViewModel();
        return instance;
    }


    public void addRace(Race race) {

        //to be implemented
    }

}