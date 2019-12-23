package ro.ase.stampcollector.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import ro.ase.stampcollector.User;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<User>> mUsers;

    public HomeViewModel(ArrayList<User> users) {
        this.mUsers = new MutableLiveData<>(users);

    }


    public ArrayList<User> getUsers() {
        return mUsers.getValue();
    }
}