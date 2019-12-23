package ro.ase.stampcollector;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("select * from users")
    List<User> getUserList();

    @Query("select * from stamps")
    List<Stamp2> getStampsList();

    @Query("SELECT * FROM users WHERE users.username LIKE :username")
    User getAccount(String username);

    @Query("select * from stamps where id = :idstamp")
    Stamp2 getStampsById(int idstamp);

    @Query("select * from users where id = :iduser")
    User getUserById(int iduser);

    @Insert
    void saveUser(User user);

    @Insert
    void saveStamp(Stamp2 stamp);

    @Insert
    void saveStamps(List<Stamp2> stamps);






//    @Query("select * from users")
//    List<UserWithStamps> getUserRelation();



}
