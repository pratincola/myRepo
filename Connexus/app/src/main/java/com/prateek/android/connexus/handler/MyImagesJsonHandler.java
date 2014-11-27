package com.prateek.android.connexus.handler;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prateek.android.connexus.ConnexusImages;
import com.prateek.android.connexus.OnDownloadListener;
import com.prateek.android.connexus.SingleStreamFragment;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by prateek on 11/8/14.
 */
public class MyImagesJsonHandler extends JsonHandler{
    private static final String TAG = "MyImagesJsonHandler";
    OnDownloadListener fragment;

    public MyImagesJsonHandler(OnDownloadListener fragment) {
        Log.i(TAG, " Initializing MyImagesJsonHandler");
        this.fragment = fragment;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        Log.i(TAG, " JSONObject Success : " + response.toString());
        Gson gson = new Gson();
        ConnexusImages s = gson.fromJson(response.toString(), ConnexusImages.class);
        Log.i(TAG, " JSONObject Success received Stream info: " + s.toString());
        Log.i(TAG, " fragment: " +  fragment.toString());
        Log.i(TAG, " instance " + fragment);
            fragment.onDownloadSuccess(s);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        Log.i(TAG, " JSONArray Success : " + response.toString());
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<ConnexusImages>>(){}.getType();
        List<ConnexusImages> s = gson.fromJson(response.toString(), collectionType);
        Log.i(TAG, " JSONArray Success received Stream info: " + s.toString());
        fragment.onDownloadSuccess(s);

    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
        Log.i(TAG, " JSONObject Success responseString: " + responseString);
        super.onSuccess(statusCode, headers, responseString);
    }

}
