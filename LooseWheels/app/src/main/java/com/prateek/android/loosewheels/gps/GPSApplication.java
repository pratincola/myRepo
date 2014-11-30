package com.prateek.android.loosewheels.gps;

import android.app.Application;
import android.util.Log;
import com.littlefluffytoys.littlefluffylocationlibrary.LocationLibrary;

/**
 * Created by prateek on 11/28/14.
 */
public class GPSApplication extends Application {
    private static final String TAG =  "GPSApplication";
    public static final long ALARM_FREQUENCY = 10000; // 10 sec
    public static final int MAXIMUM_LOCATION_AGE = (1/2) * 60 * 1000; // 30 secs


    @Override
    public void onCreate() {
        super.onCreate();
        // Show debugging information
        Log.d("FluffyApplication", "onCreate()");

        LocationLibrary.showDebugOutput(true);

        // Default call would be the following:
        // LocationLibrary.initialiseLibrary(getBaseContext(),
        //     "com.cookbook.fluffylocation");

        // For testing, make request every 1 minute, and force a location update
        // if one hasn't happened in the last 2 minutes
        LocationLibrary.initialiseLibrary(getBaseContext(), ALARM_FREQUENCY, MAXIMUM_LOCATION_AGE, "com.prateek.android.loosewheels.gps");
    }
//    @Override
//    public void onCreate(){
//        Log.i(TAG, " --------------- ");
//        super.onCreate();
//        LocationLibrary.showDebugOutput(true);
//        LocationLibrary.useFineAccuracyForRequests(true);
//        LocationLibrary.initialiseLibrary(getBaseContext(), ALARM_FREQUENCY, MAXIMUM_LOCATION_AGE, "com.prateek.android.loosewheels.gps");
//
//    }

//    public static Context getAppContext() {
//        return ApplicationVexPromoter.context;
//    }

}
