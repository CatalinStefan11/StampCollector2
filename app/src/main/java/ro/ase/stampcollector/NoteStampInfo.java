package ro.ase.stampcollector;

import android.os.Parcel;
import android.os.Parcelable;

public class NoteStampInfo implements Parcelable {
    private String mTitle;
    private String mText;

    public NoteStampInfo(){};

    public NoteStampInfo(String title, String text) {
        mTitle = title;
        mText = text;
    }

    private NoteStampInfo(Parcel parcel) {
        mTitle = parcel.readString();
        mText = parcel.readString();
    }


    public static final Creator<NoteStampInfo> CREATOR = new Creator<NoteStampInfo>() {
        @Override
        public NoteStampInfo createFromParcel(Parcel in) {
            return new NoteStampInfo(in);
        }

        @Override
        public NoteStampInfo[] newArray(int size) {
            return new NoteStampInfo[size];
        }
    };

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }


    @Override
    public String toString() {
        return mTitle + mText;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mText);
    }

}
