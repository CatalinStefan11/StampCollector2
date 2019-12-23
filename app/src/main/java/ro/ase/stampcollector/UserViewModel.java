package ro.ase.stampcollector;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class UserViewModel extends ViewModel {

    private MediatorLiveData<Stamp2> stamps;
    private  UserRepository mUserRepository;

    public UserViewModel(Context context)
    {
        mUserRepository = UserRepository
                .getInstance(StampsDatabase.getInstance(context).userDao());
    }



    @NonNull
    public LiveData<Stamp2> getPost() {
        return stamps;
    }

    public void createUser(String username, String password)
    {
        mUserRepository.insertUser(username,password);
    }

    public boolean checkValidLogin(String username, String password)
    {
        return mUserRepository.isValidAccount(username,password);
    }






}
