package ro.ase.stampcollector;

import androidx.lifecycle.LifecycleObserver;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "stamps")
public final class StampInfo {

    @Ignore
    private String uid;

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private String issuedOn;
    private String color;
    private int quantity;

    public StampInfo(String title, String descrption, String issuedOn, String color, int quantity) {
        this.title = title;
        this.description = descrption;
        this.issuedOn = issuedOn;
        this.color = color;
        this.quantity = quantity;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StampInfo stampInfo = (StampInfo) o;
        return quantity == stampInfo.quantity &&
                Objects.equals(title, stampInfo.title) &&
                Objects.equals(description, stampInfo.description) &&
                Objects.equals(issuedOn, stampInfo.issuedOn) &&
                Objects.equals(color, stampInfo.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, issuedOn, color, quantity);
    }


}
