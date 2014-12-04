package com.prateek.android.loosewheels.db;

import java.util.ArrayList;

/**
 * Created by prateek on 11/28/14.
 */
public class UserObject {

    String username;
    String last_long;
    String last_lat;

    public UserObject(String username, String last_lat, String last_long) {
        this.username = username;
        this.last_long = last_long;
        this.last_lat = last_lat;
    }

    public String getUsername() {
        return username;
    }

    public String getLast_long() {
        return last_long;
    }

    public Double getLast_long_double() {
        return Double.parseDouble(last_long);
    }

    public String getLast_lat() {
        return last_lat;
    }

    public Double getLast_lat_double() {
        return Double.parseDouble(last_lat);
    }

    @Override
    public String toString() {
        return "UserObject{" +
                "users=" + username +
                ", longitude=" + last_long +
                ", latitude=" + last_lat +
                '}';
    }
}
