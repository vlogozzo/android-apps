package com.example.vince.assignment3;

import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable {
    private String city;
    private String name;
    private String sport = "";
    private String mvp = "";
    private String stadium = "";


    public Team(String city, String name, String sport, String mvp, String stadium) {
        this.city = city;
        this.name = name;
        if (sport != null)
            this.sport = sport;
        if (mvp != null)
            this.mvp = mvp;
        if (stadium != null)
            this.stadium = stadium;
    }

    protected Team(Parcel in) {
        city = in.readString();
        name = in.readString();
        sport = in.readString();
        mvp = in.readString();
        stadium = in.readString();
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

    public String getStadium() {
        return stadium;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city);
        dest.writeString(name);
        dest.writeString(sport);
        dest.writeString(mvp);
        dest.writeString(stadium);
    }


    @Override
    public String toString() {
        return String.format("%s %s %s %s %s", city, name, sport, mvp, stadium);
    }
}
