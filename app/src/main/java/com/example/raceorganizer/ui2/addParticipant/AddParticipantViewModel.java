package com.example.raceorganizer.ui2.addParticipant;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddParticipantViewModel extends ViewModel {
    private String FirstName,LastName,Age,Number;
    public AddParticipantViewModel(){
        //TODO implement the functionality in the participant repository
        FirstName = "";
        LastName = "";
        Age = "";
        Number = "";
    }

    public void makeParticipant(){
        //TODO make something in the background
    }

    public String getAge() {
        return Age;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
