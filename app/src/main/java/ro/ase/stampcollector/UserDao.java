package ro.ase.stampcollector;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;


import java.util.List;

@Dao
public interface UserDao {


    @Insert
    public void addUser(User user);


    @Query("SELECT * FROM users WHERE username LIKE :username")
    public User getAccount(String username);

    @Delete
    public void deleteUser(User user);

    @Update
    void updateUser(User user);

    @Query("SELECT * FROM users WHERE id = :u_id")
    public User getUserById(long u_id);



}
