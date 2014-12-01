package com.prateek.android.loosewheels.integration;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prateek.android.loosewheels.integration.UserObject;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by prateek on 11/28/14.
 */
public class GPSData extends JsonHandler {

    private static final String TAG = "GPSData";


    public GPSData() {
        Log.i(TAG, " Initializing GPSData");

    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        Log.i(TAG, " JSONObject Success : " + response.toString());
        Gson gson = new Gson();
        UserObject u = gson.fromJson(response.toString(), UserObject.class);
        Log.i(TAG, " JSONObject Success received Stream info: " + u.toString());
//        fragment.onDownloadSuccess(s);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        Log.i(TAG, " JSONArray Success : " + response.toString());
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<UserObject>>(){}.getType();
        List<UserObject> u = gson.fromJson(response.toString(), collectionType);
        Log.i(TAG, " JSONArray Success received Stream info: " + u.toString());
//        fragment.onDownloadSuccess(s);

    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
        Log.i(TAG, " JSONObject Success responseString: " + responseString);
        super.onSuccess(statusCode, headers, responseString);
    }


}
