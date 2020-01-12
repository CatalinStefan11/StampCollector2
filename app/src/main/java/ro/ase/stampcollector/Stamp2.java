package ro.ase.stampcollector;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;


import java.util.Objects;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "stamps",
            foreignKeys = @ForeignKey(onDelete = CASCADE,entity = User.class,
            parentColumns = "id",
            childColumns = "user_id"),
        indices = @Index("user_id"))
public class Stamp2 {


        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        public int id;
        @ColumnInfo(name = "user_id")
        private long userId;
        @ColumnInfo(name = "title")
        private String title;
        @ColumnInfo(name = "description")
        private String description;
        @ColumnInfo(name = "issuedOn")
        private String issuedOn;
        @ColumnInfo(name = "color")
        private String color;
        @ColumnInfo(name = "quantity")
        private int quantity;

        public Stamp2( String title, String description, String issuedOn, String color, int quantity) {

            this.title = title;
            this.description = description;
            this.issuedOn = issuedOn;
            this.color = color;
            this.quantity = quantity;
        }

        @Ignore
        public Stamp2()
        {

        }

    @Override
    public String toString() {
        return

                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", issuedOn='" + issuedOn + '\'' +
                ", color='" + color + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getIssuedOn() {
            return issuedOn;
        }

        public String getColor() {
            return color;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setIssuedOn(String issuedOn) {
            this.issuedOn = issuedOn;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }



        @Override
        public int hashCode() {
            return Objects.hash(title, description, issuedOn, color, quantity);
        }


    }


