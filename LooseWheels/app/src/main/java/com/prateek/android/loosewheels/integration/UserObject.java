package com.prateek.android.loosewheels.integration;

import java.util.ArrayList;

/**
 * Created by prateek on 11/28/14.
 */
public class UserObject {

    String username;
    String last_long;
    String last_lat;

    public UserObject(String username, String last_long, String last_lat) {
        this.username = username;
        this.last_long = last_long;
        this.last_lat = last_lat;
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
