package ro.ase.stampcollector;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private int id;
    private String name;
    private String email;
    private String companyName;
    private String city;
    private String phone;
    private String website;



    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User(){}

    public User(int id, String name, String email, String companyName, String city,
                String phone, String website) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.companyName = companyName;
        this.city = city;
        this.phone = phone;
        this.website = website;
    }

    public User(Parcel parcel)
    {
        this.id = parcel.readInt();
        this.name = parcel.readString();
        this.email = parcel.readString();
        this.companyName = parcel.readString();
        this.city = parcel.readString();
        this.phone = parcel.readString();
        this.website = parcel.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(companyName);
        dest.writeString(city);
        dest.writeString(phone);
        dest.writeString(website);
    }


}


