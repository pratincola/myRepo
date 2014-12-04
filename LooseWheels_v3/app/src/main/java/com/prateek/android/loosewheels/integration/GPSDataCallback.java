package com.prateek.android.loosewheels.integration;

import com.prateek.android.loosewheels.db.UserObject;

import java.util.List;

/**
 * Created by prateek on 12/1/14.
 */
public interface GPSDataCallback{
    public void onGPSReceive(UserObject u);
    public void onGPSReceive(List<UserObject> u);
}