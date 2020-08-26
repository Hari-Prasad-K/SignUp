package com.example.signup.RoomDataBase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "registration")
public class Register {

    @ColumnInfo(name="user_name")
    private String name;

    @ColumnInfo(name="user_address")
    private String address;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="user_email")
    private String email;

    @ColumnInfo(name = "mobile_no")
    private String mobile;

    @ColumnInfo(name="user_password")
    private String password;

    @ColumnInfo(name="user_location")
    private String location;


    @Ignore
    public Register(){}
    public Register(String name, String address, String email, String mobile, String password,String location)
    {
        this.name=name;
        this.address=address;
        this.email=email;
        this.mobile = mobile;
        this.password = password;
        this.location=location;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
