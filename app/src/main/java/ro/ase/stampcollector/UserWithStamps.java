//package ro.ase.stampcollector;
//
//import androidx.room.Embedded;
//import androidx.room.Relation;
//
//import java.util.List;
//
//public class UserWithStamps {
//    @Embedded
//    public User user;
//    @Relation(parentColumn = "id", entityColumn = "userId")
//    public List<StampInfo> stamps;
//}