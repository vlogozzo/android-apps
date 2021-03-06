package com.example.vince.assignment4;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Team implements Parcelable {

    private int id = 0;
    private String city;
    private String name;
    private String sport = "";
    private String mvp = "";
    private String image;


    public Team(String city, String name, String sport, String mvp, String image) {
        this.city = city;
        this.name = name;
        if (sport != null)
            this.sport = sport;
        if (mvp != null)
            this.mvp = mvp;
        this.image = image;
    }

    public Team(int id, String city, String name, String sport, String mvp, String image) {
        this.id = id;
        this.city = city;
        this.name = name;
        if (sport != null)
            this.sport = sport;
        if (mvp != null)
            this.mvp = mvp;
        if (image != null)
            this.image = image;
    }

    protected Team(Parcel in) {
        id = in.readInt();
        city = in.readString();
        name = in.readString();
        sport = in.readString();
        mvp = in.readString();
        image = in.readString();
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getSport() {
        return sport;
    }

    public String getMvp() {
        return mvp;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(city);
        dest.writeString(name);
        dest.writeString(sport);
        dest.writeString(mvp);
        dest.writeString(image);
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %s %s", id, city, name, sport, mvp);
    }
}
