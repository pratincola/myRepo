package com.prateek.android.connexus.handler;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prateek.android.connexus.ConnexusStream;
import com.prateek.android.connexus.ViewAllFragment;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by prateek on 11/7/14.
 */
public class MyJsonHandler extends JsonHandler {
    private static final String TAG = "MyJsonHandler";
    ViewAllFragment fragment;
//    ImageAdapter3 imageAdapter;
//    public MyJsonHandler(ImageAdapter3 imageAdapter ) {
//        Log.i(TAG, " Initializing MyJsonHandler");
//        this.imageAdapter = imageAdapter;
//    }

        public MyJsonHandler(ViewAllFragment fragment) {
        Log.i(TAG, " Initializing MyJsonHandler");
        this.fragment = fragment;
    }



//        @Override
//        public void onSuccess(JSONObject myobj) {
//            Gson gson = new Gson();
//            ConnexusStream s = gson.fromJson(myobj.toString(), ConnexusStream.class);
//            fragment.onDownloadSuccess(s);
//        }
//
//
//        @Override
//        public void onFailure(Throwable arg0, JSONObject arg1) {
//            // TODO Auto-generated method stub
//            super.onFailure(arg0, arg1);
//            fragment.onDownloadFailure();
//        }


    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        Log.i(TAG, " JSONObject Success : " + response.toString());
        Gson gson = new Gson();
        Object s = gson.fromJson(response.toString(), ConnexusStream.class);
//        s instanceof ConnexusStream ? ((ConnexusStream) s) : null;
        Log.i(TAG, " JSONObject Success received Stream info: " + s.toString());
        fragment.onDownloadSuccess(s);
//            super.onSuccess(statusCode, headers, response);

    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        Log.i(TAG, " JSONArray Success : " + response.toString());
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<ConnexusStream>>(){}.getType();
        List<ConnexusStream> s = gson.fromJson(response.toString(), collectionType);

//        ConnexusStream s = gson.fromJson(response.toString(), ConnexusStream.class);
        Log.i(TAG, " JSONObject Success received Stream info: " + s.toString());
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