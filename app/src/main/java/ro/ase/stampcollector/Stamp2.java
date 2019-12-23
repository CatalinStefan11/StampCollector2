package ro.ase.stampcollector;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "stamps")
public class Stamp2 {


        @PrimaryKey(autoGenerate = true)
        private int id;
        @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")
        private int userId;
        private String title;
        private String description;
        private String issuedOn;
        private String color;
        private int quantity;

        public Stamp2(int id, int userId, String title, String description, String issuedOn, String color, int quantity) {
            this.id = id;
            this.userId = userId;
            this.title = title;
            this.description = description;
            this.issuedOn = issuedOn;
            this.color = color;
            this.quantity = quantity;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
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


