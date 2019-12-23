package ro.ase.stampcollector;

import java.util.List;

public class UserRepository {

    private final UserDao mUserDao;
    private static UserRepository instance;


    private UserRepository(UserDao userDao)
    {
        this.mUserDao = userDao;
    }

    public static UserRepository getInstance(UserDao userDao)
    {
        if(instance == null)
        {
            instance = new UserRepository(userDao);
        }
        return instance;
    }

    public boolean isValidAccount(String username, final String password)
    {
        User user = mUserDao.getAccount(username);
        return user.getPassword().equals(password);
    }

    public void insertUser(String username, String password)
    {
        User account = new User(username, password);
        mUserDao.saveUser(account);
    }

    public List<Stamp2> getStamps(){
        List<Stamp2> stamps = mUserDao.getStampsList();
        return stamps;
    }


}
