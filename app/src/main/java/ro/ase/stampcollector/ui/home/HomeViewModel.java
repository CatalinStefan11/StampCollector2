package ro.ase.stampcollector.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import ro.ase.stampcollector.Collector;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Collector>> mUsers;

    public HomeViewModel(ArrayList<Collector> collectors) {
        this.mUsers = new MutableLiveData<>(collectors);

    }


    public ArrayList<Collector> getUsers() {
        return mUsers.getValue();
    }
}