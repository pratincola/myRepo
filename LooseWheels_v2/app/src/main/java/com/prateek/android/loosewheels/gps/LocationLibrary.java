package com.prateek.android.loosewheels.gps;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by prateek on 11/28/14.
 */
public class LocationLibrary extends Service implements
        GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener,
        LocationListener {

    private final String TAG = "LocationLibrary";
    IBinder mBinder = new LocalBinder();

    private LocationClient mLocationClient;
    private LocationRequest mLocationRequest;
    // Flag that indicates if a request is underway.
    private boolean mInProgress;

    private Boolean servicesAvailable = false;

    public class LocalBinder extends Binder {
        public LocationLibrary getServerInstance() {
            return LocationLibrary.this;
        }
    }

    @Override
    public void onCreate(){

        mInProgress = false;
        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create();
        // Use high accuracy
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        // Set the update interval to 5 seconds
        mLocationRequest.setInterval(LocationUtils.UPDATE_INTERVAL_IN_MILLISECONDS);
        // Set the fastest update interval to 1 second
        mLocationRequest.setFastestInterval(LocationUtils.FAST_INTERVAL_CEILING_IN_MILLISECONDS);

        servicesAvailable = servicesConnected();
        /*
         * Create a new location client, using the enclosing class to
         * handle callbacks.
         */
        mLocationClient = new LocationClient(this, this, this);

    }

    private boolean servicesConnected() {
        // Check that Google Play services is available
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            return true;
        } else {
            return false;
        }
    }

    public int onStartCommand (Intent intent, int flags, int startId)
    {
        super.onStartCommand(intent, flags, startId);

        if(!servicesAvailable || mLocationClient.isConnected() || mInProgress)
            return START_STICKY;

        setUpLocationClientIfNeeded();
        if(!mLocationClient.isConnected() || !mLocationClient.isConnecting() && !mInProgress)
        {
            Log.i(TAG, DateFormat.getDateTimeInstance().format(new Date()) + ": Started");
            mInProgress = true;
            mLocationClient.connect();
        }

        return START_STICKY;
    }
    /*
    * Create a new location client, using the enclosing class to
    * handle callbacks.
    */
    private void setUpLocationClientIfNeeded()
    {
        if(mLocationClient == null)
            mLocationClient = new LocationClient(this, this, this);
    }


    @Override
    public void onDestroy(){
        // Turn off the request flag
        mInProgress = false;
        if(servicesAvailable && mLocationClient != null) {
            mLocationClient.removeLocationUpdates(this);
            // Destroy the current location client
            mLocationClient = null;
        }
        // Display the connection status
        // Toast.makeText(this, DateFormat.getDateTimeInstance().format(new Date()) + ": Disconnected. Please re-connect.", Toast.LENGTH_SHORT).show();
        Log.i(TAG, DateFormat.getDateTimeInstance().format(new Date()) + ": Stopped");
        super.onDestroy();
    }


    // Define the callback method that receives location updates
    @Override
    public void onLocationChanged(Location location) {
        // Report to the UI that the location was updated
        String msg = Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        Log.i("debug", msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    /*
    * Called by Location Services when the request to connect the
    * client finishes successfully. At this point, you can
    * request the current location or start periodic updates
    */
    @Override
    public void onConnected(Bundle bundle) {
        // Request location updates using static settings
        PendingIntent locationIntent;
        Intent intent = new Intent(this, LocationLoggerServiceManager.class);
        locationIntent = PendingIntent.getBroadcast(getApplicationContext(), 14872, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        mLocationClient.requestLocationUpdates(mLocationRequest, locationIntent);
//        mLocationClient.requestLocationUpdates(mLocationRequest, this);
        Log.i(TAG, DateFormat.getDateTimeInstance().format(new Date()) + ": Connected");

    }

    /*
    * Called by Location Services if the connection to the
    * location client drops because of an error.
    */
    @Override
    public void onDisconnected() {
        // Turn off the request flag
        mInProgress = false;
        // Destroy the current location client
        mLocationClient = null;
        // Display the connection status
        // Toast.makeText(this, DateFormat.getDateTimeInstance().format(new Date()) + ": Disconnected. Please re-connect.", Toast.LENGTH_SHORT).show();
        Log.i(TAG, DateFormat.getDateTimeInstance().format(new Date()) + ": Disconnected");
    }

    /*
    * Called by Location Services if the attempt to
    * Location Services fails.
    */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        mInProgress = false;

        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            // If no resolution is available, display an error dialog
        } else {

        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public String getTime() {
        SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return mDateFormat.format(new Date());
    }

}
