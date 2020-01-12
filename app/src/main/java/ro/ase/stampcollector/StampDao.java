package ro.ase.stampcollector;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StampDao {

    @Insert
    public  void addStamp(Stamp2 stamps);

    @Query("select * from stamps where user_id = :u_id")
    public List<Stamp2> getAllStamps(long u_id);

    @Update
    void update(Stamp2 stamp);

    @Delete
    public void deleteStamp(Stamp2 stamp2);

    @Query("select * from stamps where id = :stamp_id")
    public Stamp2 getStamp(long stamp_id);



}
