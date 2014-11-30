package com.prateek.android.loosewheels.gps;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationClient;

/**
 * Created by prateek on 11/28/14.
 */
public class LocationLoggerServiceManager extends BroadcastReceiver {
    private SharedPreferences mPrefs;
    private final String TAG = "LocationLoggerServiceManager";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "<<<<<<<<<>>>>>>>>");
        Location location = (Location) intent.getExtras().get(LocationClient.KEY_LOCATION_CHANGED);
        Log.i(TAG, location.toString());
        ComponentName comp = new ComponentName(context.getPackageName(), LocationLibrary.class.getName());
        ComponentName service = context.startService(new Intent().setComponent(comp));

        if (null == service){
            // something really wrong here
            Log.e(TAG, "Could not start service " + comp.toString());
        }
        else {
            Log.e(TAG, "Received unexpected intent " + intent.toString());
        }
    }
}
