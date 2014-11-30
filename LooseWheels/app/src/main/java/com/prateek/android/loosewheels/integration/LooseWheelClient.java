package com.prateek.android.loosewheels.integration;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;

/**
 * Created by prateek on 11/28/14.
 */
public class LooseWheelClient {

    private static final String TAG = "LooseWheelClient";

    private static final String BASE_URL = "http://loose-wheels-two.appspot.com/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.i(TAG, "Get: " + getAbsoluteUrl(url) + " " + params + " " + responseHandler.toString());
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.i(TAG, "Post: " + url + " " + params + " " + responseHandler.toString());
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}

