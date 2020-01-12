package ro.ase.stampcollector;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {User.class, Stamp2.class}, version =  1, exportSchema = false)
public abstract class StampsDatabase extends RoomDatabase {
    private static final String DB_NAME = "stamps_collector_database4";
    private static StampsDatabase instanace;
    public abstract UserDao userDao();
    public abstract StampDao stampDao();

    public synchronized static StampsDatabase getInstance(Context context){
        if (instanace == null) {
            instanace = Room.databaseBuilder(context, StampsDatabase.class, DB_NAME)
                    .allowMainThreadQueries().build();
        }
        return instanace;
    }


}
