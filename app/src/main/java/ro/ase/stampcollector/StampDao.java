package ro.ase.stampcollector;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StampDao {


    @Insert
    public void insert(StampInfo stamp);

    @Insert
    public void insert(List<StampInfo> stamps);

    @Query("SELECT * FROM stamps")
    public List<StampInfo> getAll();

    @Query("SELECT FROM stamps WHERE id = :idparameter")
    public StampInfo getById(long idparameter);

    @Query("DELETE FROM stamps  where id = :id1")
    public void deletebyId(long id1);
}
