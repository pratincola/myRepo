package com.prateek.android.connexus.handler;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prateek.android.connexus.ConnexusImages;
import com.prateek.android.connexus.NearByFragment;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by prateek on 11/10/14.
 */
public class MyNearByImagesJsonHandler extends JsonHandler{
        private static final String TAG = "MyImagesJsonHandler";
        NearByFragment fragment;
        Class gsonClass ;

        public MyNearByImagesJsonHandler(NearByFragment fragment) {
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


//            super.onSuccess(statusCode, headers, response);

        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
            Log.i(TAG, " JSONArray Success : " + response.toString());
            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<ConnexusImages>>(){}.getType();
            List<ConnexusImages> s = gson.fromJson(response.toString(), collectionType);

//        ConnexusStream s = gson.fromJson(response.toString(), ConnexusStream.class);
            Log.i(TAG, " JSONArray Success received Stream info: " + s.toString());
            fragment.onDownloadSuccess(s);
//            super.onSuccess(statusCode, headers, response);
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, String responseString) {
            Log.i(TAG, " JSONObject Success responseString: " + responseString);
            super.onSuccess(statusCode, headers, responseString);
        }
        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//            Log.e(TAG, "There was a problem in retrieving the url : " + errorResponse.toString());
            super.onFailure(statusCode, headers, throwable, errorResponse);
            fragment.onDownloadFailure();
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//            Log.e(TAG,"There was a problem in retrieving the url : " + errorResponse.toString());
            super.onFailure(statusCode, headers, throwable, errorResponse);
            fragment.onDownloadFailure();
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            super.onFailure(statusCode, headers, responseString, throwable);
        }

    }


