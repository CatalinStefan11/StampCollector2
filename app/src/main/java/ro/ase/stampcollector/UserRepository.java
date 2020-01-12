package ro.ase.stampcollector;

import android.content.Context;
import java.util.List;


public class UserRepository  {

    private final UserDao mUserDao;
    private final StampDao mStampDao;
    private static UserRepository instance;
    private User currentUser;



    private UserRepository(UserDao userDao, StampDao stampDao)
    {
        this.mUserDao = userDao;
        this.mStampDao = stampDao;
    }

    public static UserRepository getInstance(Context context)
    {
        if(instance == null)

        {

            instance = new UserRepository(StampsDatabase.getInstance(context).userDao(),
                    StampsDatabase.getInstance(context).stampDao());
        }
        return instance;
    }



    public User getCurrentUser()
    {
        return currentUser;
    }


    public User login(String username, final String password)
    {
        User user = mUserDao.getAccount(username);
        if(user.getPassword().equals(password))
        {
            currentUser = user;
            return user;

        }
        else
        {
            return null;
        }
    }

    public void deleteUser(User user)
    {
        mUserDao.deleteUser(user);
    }

    public void updateUser(User user){
        mUserDao.updateUser(user);
    }

    public User getUserById(long id){
        return mUserDao.getUserById(id);
    }

    public void addUser(String username, String passowrd){

        User user = new User(username, passowrd);
        mUserDao.addUser(user);
    }

    public void addStamp(User currentUser, Stamp2 stamp)
    {
        stamp.setUserId(currentUser.id);
        mStampDao.addStamp(stamp);
    }

    public List<Stamp2> getStamps(User currentUser)
    {
        return mStampDao.getAllStamps(currentUser.id);
    }

    public void updateStamp(Stamp2 stamp)
    {
        mStampDao.update(stamp);
    }

    public void deleteStamp(Stamp2 stamp) { mStampDao.deleteStamp(stamp);}

    public Stamp2 getStamp(int id)
    {

        return mStampDao.getStamp(id);
    }



}

