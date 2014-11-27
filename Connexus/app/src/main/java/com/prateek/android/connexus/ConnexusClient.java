package com.prateek.android.connexus;

import android.util.Log;

import com.loopj.android.http.*;
import com.prateek.android.connexus.handler.JsonHandler;

import org.json.JSONException;

/**
 * Created by prateek on 11/4/14.
 */
public class ConnexusClient {
    private static final String TAG = "ConnexusClient";

    private static final String BASE_URL = "http://connexus-phase2.appspot.com/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.i(TAG, "Get: "  + getAbsoluteUrl(url) + " " + params + " " + responseHandler.toString());
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.i(TAG, "Post: "  + url + " " + params + " " + responseHandler.toString());
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}
class MyConnexusClientUsage {
    public void getStream(JsonHandler jsonHandler, RequestParams params, String fileName) throws JSONException {
        ConnexusClient.get(fileName, params, jsonHandler);
    }

//class MyConnexusClientUsage {
//    public void getStream (String fileName, StreamAdapter2.MyJsonHandler jsonHandler) throws JSONException {
//        ConnexusClient.get(fileName, null, jsonHandler);
//    }
}

