package ro.ase.stampcollector;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "users", indices = @Index(value = "username", unique = true))
public class User {



    @PrimaryKey(autoGenerate =  true)
    public long id;
    @NonNull
    @ColumnInfo(name = "username")
    private String username;
    @NonNull
    @ColumnInfo(name = "password")
    private String password;




    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


}
