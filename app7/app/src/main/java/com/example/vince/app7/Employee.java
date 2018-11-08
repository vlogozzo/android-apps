package com.example.vince.app7;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Employee implements Parcelable {
    private String name;
    private String dept;
    private String year;

    public Employee(String name, String dept, String year) {
        this.name = name;
        this.dept = dept;
        this.year = year;
    }

    protected Employee(Parcel in) {
        name = in.readString();
        dept = in.readString();
        year = in.readString();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public void setName(String name) {
        this.name = name;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getYear() {
        return year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(dept);
        dest.writeString(year);
    }
}
