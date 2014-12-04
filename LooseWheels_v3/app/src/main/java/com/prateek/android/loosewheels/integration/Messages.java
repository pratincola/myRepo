package com.prateek.android.loosewheels.integration;

import android.util.Log;

import com.littlefluffytoys.littlefluffylocationlibrary.LocationInfo;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;

/**
 * Created by prateek on 11/29/14.
 */
public class Messages {
    private final String TAG = "Messages";

    LocationInfo locationInfo;
    JsonHandler jsonHandler = new GPSData();
    MyLooseWheelClientUsage client = new MyLooseWheelClientUsage();
    RequestParams a,b;

    public Messages(LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }

    public void sendPushUserData(){
        a = new RequestParams();
        a.put("user", "tanin");
        a.put("lat", locationInfo.lastLat);
        a.put("long", locationInfo.lastLong);
        a.put("time", locationInfo.lastLocationBroadcastTimestamp);
        a.put("acc", locationInfo.lastAccuracy);
        a.put("update", locationInfo.lastLocationUpdateTimestamp);

        Log.i(TAG, " <<<<<<<<<<<<>>>>>>>>>> " +  a.toString());
        try {
            client.postStream("pushuserdata", a, jsonHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendGetUserData(){
        Log.i(TAG, " <<<<<<<<<<<<>>>>>>>>>> " +  a.toString());
        b = new RequestParams();
        b.put("user", "rohan");
        try {
            client.getStream("getuserdata", b, jsonHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}
